#include <stdlib.h>
#include <fstream>
#include <gtk/gtk.h>
#include <sys/stat.h>
#include <vector>
#include <string>
#include <libintl.h>
#include <locale.h>
using namespace std;
#define _(x) gettext(x)

///Macros/defines
#define TAB_COLS 3
#define ROW_SPACING 3
#define COL_SPACING 3
///Classes, typedefs

typedef struct
{
    bool enabled;string name, statF, instCmd, startCmd;
} section;

///Global variables
vector<section> applications;
string configfile = "./gtkinst.cfg";

inline void parseConfig()
{
    string s;
    fstream f;
    f.open(configfile.c_str(), fstream::in);
    section a;
    bool first = false;
    while(!f.eof())
    {
        getline(f,s);
        switch(s[0])
        {
            case '[':
            {
                if(first){applications.push_back(a);}
                first = true;
                a.name = s.substr(1,s.length()-2);
                break;
            }
            case 'c': //Check: file to stat
            {
                a.statF = s.substr(6,s.length());break;
            }
            case 'i'://Install
            {
                a.instCmd = s.substr(8.,s.length());break;
            }
            case 's': //Start
            {
                a.startCmd = s.substr(6,s.length());break;
            }
            default:break;
        }
    }
    applications.push_back(a);
    f.close();
}

static void installApplication(GtkWidget *wid, gpointer data)
{
    system(applications[GPOINTER_TO_INT(data)].instCmd.c_str());
}

static void runApplication(GtkWidget *wid, gpointer data)
{
    system(applications[GPOINTER_TO_INT(data)].startCmd.c_str());
}

int main (int argc, char *argv[])
{
  gtk_init (&argc, &argv);
  //Some gettext calls
  setlocale(LC_MESSAGES,"");
  bindtextdomain("GtkInstaller", "./");
  textdomain("GtkInstaller");

  parseConfig();

  GtkWidget *win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
  GtkWidget *table = gtk_table_new(TAB_COLS, applications.size(), true);
  GtkWidget *installButton = NULL;
  GtkWidget *startButton = NULL;
  GtkWidget *label = NULL;

  //Set some parameters
  gtk_window_set_title (GTK_WINDOW (win), _("GtkInstaller"));
  gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  gtk_widget_realize (win);
  gtk_table_set_row_spacings(GTK_TABLE(table), ROW_SPACING);
  gtk_table_set_col_spacings(GTK_TABLE(table), COL_SPACING);
  g_signal_connect (win, "destroy", gtk_main_quit, NULL);

  //Init menues


  //Init fixed container and add widgets
  for(unsigned short i=1;i<=applications.size();i++)
    {
        struct stat statBuf;
        installButton = gtk_button_new_with_label(_("Install"));
        startButton = gtk_button_new_with_label(_("Run"));
        gtk_widget_set_sensitive(startButton, FALSE);
        label = gtk_label_new(_(applications[i-1].name.c_str()));
        g_print(applications[i-1].statF.c_str());
        if(stat(applications[i-1].statF.c_str(), &statBuf)==0)
            {
                gtk_widget_set_sensitive(installButton,false);
                gtk_widget_set_sensitive(startButton, true);
            }
        gtk_signal_connect(GTK_OBJECT(installButton), "clicked", GTK_SIGNAL_FUNC(installApplication), GINT_TO_POINTER(i-1));
        gtk_signal_connect(GTK_OBJECT(startButton), "clicked", GTK_SIGNAL_FUNC(runApplication), GINT_TO_POINTER(i-1));
        gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, i, i+1);
        gtk_table_attach_defaults(GTK_TABLE(table), installButton, 1, 2, i, i+1);
        gtk_table_attach_defaults(GTK_TABLE(table), startButton, 2, 3, i, i+1);
    }

  /* Enter the main loop */
  gtk_container_add (GTK_CONTAINER (win), table);
  gtk_widget_show_all (win);
  gtk_main ();
  return 0;
}
