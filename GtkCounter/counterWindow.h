#include <stdlib.h>
#include <gtk/gtk.h>
#include <sqlite3.h>
#include <libintl.h>
#include <boost/lexical_cast.hpp>
#include <map>
using namespace std;
using namespace boost;

///Macros and defines
#define TAB_COLS 5
#define NAME "/.gtkcounter.db"
#define _(x) gettext(x)

///Global variables
sqlite3 *db;

static void add(GtkWidget *wid, gpointer data)
{

}

static void set(GtkWidget *wid, gpointer data)
{

}

inline void createCounterTable(string table)
{
    sqlite3_exec(db, "CREATE TABLE IF NOT EXISTS" + table.c_str() + " (index varchar(50), value mediumint);", NULL, NULL, NULL, NULL);
}

int updateCounterData(string table, map<string, long> *results)
{
  static int numLines
  char** queryResult;
  sqlite3_get_table(db, "SELECT * FROM " + table.c_str() + ";", &queryResult, &numLines, NULL, 0);
  for(int i = 1; i <= numLines;i++)
    {
        *results[queryResult[i*2]] = lexical_cast<long>(queryResult[(i*2)+1]); //Set the appropriate value in the map. See sqlite3 documentation (sqlite3_get_table) to understand syntax.
    }
  return numLines;
}
void createCounterWindow(string table)
{
  map<string, long> values;
  int numLines;
  static struct stat statbuf;
  vector<GtkWidget*> addButtons;
  vector<GtkWidget*> setButtons;
  vector<GtkWidget*> addFields;
  vector<GtkWidget*> setFields;

  //Create sqlite database if not exists
  if(stat(name.c_str(),&statbuf)!=0)
      {
          system("sqlite3 " + name);
      }
  sqlite3_open(name.c_str());

  //Count lines in table
  numLines = updateCounterData(table, &values);

  GtkWidget *win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
  GtkWidget *table = gtk_table_new(TAB_COLS, numLines, true);
  GtkWidget *addButton;
  GtkWidget *addSpinButton;
  GtkWidget *setField;
  GtkWidget *setButton;
  GtkWidget *label;

  //Set some parameters
  gtk_window_set_title (GTK_WINDOW (win), _("GtkCounter"));
  gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  gtk_widget_realize (win);
  gtk_table_set_row_spacings(GTK_TABLE(table), ROW_SPACING);
  gtk_table_set_col_spacings(GTK_TABLE(table), COL_SPACING);
  g_signal_connect (win, "destroy", gtk_main_quit, NULL);

  ///Add widgets
  map<string, int>::iterator it != values.end();it++);
  for(int i = 0;i < numLines;i++)
    {
        label = gtk_label_new(it->first.c_str());
        setField = gtk_entry_new();
         gtk_entry_set_text(GTK_ENTRY(setField), lexical_cast<string>(it->second).c_str());
        setButton = gtk_button_new_with_label(_("Set"));
    }

  // Enter the main loop
  gtk_container_add(GTK_CONTAINER (win), table);
  gtk_widget_show_all(win);
  return 0;
}
