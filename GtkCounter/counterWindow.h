#include <stdlib.h>
#include <gtk/gtk.h>
#include <sqlite3.h>
#include <libintl.h>
#include <boost/lexical_cast.hpp>
#include <sys/stat.h>
#include <map>
#include <strings.h>
using namespace std;
using namespace boost;

///Macros and defines
#define TAB_COLS 5
#define COL_SPACING 3
#define ROW_SPACING 4
#define _(x) gettext(x)

///Global variables and variable refs
extern sqlite3 *db;

struct updateCounterParameters
{
    GtkWidget *setField;
    GtkWidget *addSpinButton;
    long oldValue;
    string name;
    string table;
};


static void addCounter(GtkWidget *wid, gpointer data)
{
    updateCounterParameters params = *reinterpret_cast<updateCounterParameters*>(data);
    //Update data: Add old value to new value and cast to string, the build SQL string and execute it
    string newValue = lexical_cast<string>(params.oldValue + lexical_cast<long>(gtk_entry_get_text(GTK_ENTRY(params.setField))));
    sqlite3_exec(db, string("UPDATE TABLE " + params.table + " SET value = " + newValue + " WHERE index = \"" + params.name + "\";").c_str(), NULL, NULL, NULL);
}

static void setCounter(GtkWidget *wid, gpointer data)
{
    updateCounterParameters params = *reinterpret_cast<updateCounterParameters*>(data);
    //Update data
    sqlite3_exec(db, string("UPDATE TABLE " + params.table + " SET value = " + gtk_entry_get_text(GTK_ENTRY(params.setField)) + " WHERE index = \"" + params.name + "\";").c_str(), NULL, NULL, NULL);
}

inline void createCounterTable(string table)
{
    sqlite3_exec(db, string("CREATE TABLE IF NOT EXISTS " + table + " (index varchar(50), value mediumint);").c_str(), NULL, NULL, NULL);
}

int updateCounterData(string table, map<string, long> *results)
{
  static int numLines;
  char **queryResult;
  sqlite3_get_table(db, strcat("SELECT * FROM ", strcat(const_cast<char*>(table.c_str()), ";")), &queryResult, &numLines, NULL, 0);
  for(int i = 1; i <= numLines;i++)
    {
        results[0][queryResult[i*2]] = lexical_cast<long>(queryResult[(i*2)+1]); //Set the appropriate value in the map. See sqlite3 documentation (sqlite3_get_table) to understand syntax.
    }
  return numLines;
}

void createCounterWindow(string sqlTable)
{
  map<string, long> values;
  int numLines;

  //Count lines in table
  numLines = updateCounterData(sqlTable, &values);

  //Declare widget pointers
  GtkWidget *win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
  GtkWidget *table = gtk_table_new(TAB_COLS, numLines, true);
  GtkWidget *addButton;
  GtkWidget *addSpinButton;
  GtkWidget *setField;
  GtkWidget *setButton;
  GtkWidget *label;

  //Set some parameters
  gtk_window_set_title (GTK_WINDOW (win), _("GtkCounter Counter"));
  gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  gtk_widget_realize (win);
  gtk_table_set_row_spacings(GTK_TABLE(table), ROW_SPACING);
  gtk_table_set_col_spacings(GTK_TABLE(table), COL_SPACING);
  g_signal_connect (win, "destroy", gtk_main_quit, NULL);

  ///Add widgets
  int i = 0;
  for(map<string, int>::iterator it = values.begin();it != values.end();it++) //Increments it and i! (it = map iterator, i = counter)
    {
        //Create a parameter set and update
        updateCounterParameters params;
        params.table = sqlTable;
        params.name = it->first;
        params.oldValue = it->second;
        params.setField = setField;
        params.addSpinButton = addSpinButton;
        //Update GUI
        label = gtk_label_new(strcat(const_cast<char*>(it->first.c_str()), ":"));
         gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, i, i+1);
        setField = gtk_entry_new();
         gtk_entry_set_text(GTK_ENTRY(setField), lexical_cast<string>(it->second).c_str());
         gtk_table_attach_defaults(GTK_TABLE(table), setField, 1, 2, i, i+1);
        setButton = gtk_button_new_with_label(_("Set"));
         gtk_signal_connect(GTK_OBJECT(setButton), "clicked", GTK_SIGNAL_FUNC(setCounter), &params);
         gtk_table_attach_defaults(GTK_TABLE(table), setButton, 2, 3, i, i+1);
        addSpinButton = gtk_spin_button_new_with_range(-999.0, 999.0, 1.0);
         gtk_table_attach_defaults(GTK_TABLE(table), addSpinButton, 3, 4, i, i+1);
        addButton = gtk_button_new_with_label(_("Add"));
         gtk_signal_connect(GTK_OBJECT(addButton), "clicked", GTK_SIGNAL_FUNC(addCounter), &params);
         gtk_table_attach_defaults(GTK_TABLE(table), addButton, 4, 5, i, i+1);
        i++;
    }

  // Enter the main loop
  gtk_container_add(GTK_CONTAINER (win), table);
  gtk_widget_show_all(win);
}
