#include <stdlib.h>
#include <fstream>
#include <gtk/gtk.h>
#include <sys/stat.h>
#include <vector>
#include <string>
#include <libintl.h>
using namespace std;

///Macros/defines
#define TAB_COLS 3
#define _(x) gettext(x)
///Classes, typedefs

typedef struct
{
    bool enabled;string name, statF, instCmd, startCmd;
} section;

///Global variables
vector<section> applications;

static void parseConfig()
{
    string s;
    fstream f;
    f.open("./gtkinst.cfg", fstream::in);
    g_print("R:%d\n",0);
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

static void processClick(GtkWidget *wid, gpointer data)
{
    system(applications[GPOINTER_TO_INT(data)].instCmd.c_str());
}

int main (int argc, char *argv[])
{
  gtk_init (&argc, &argv);

  parseConfig();

  GtkWidget *win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
  GtkWidget *table = gtk_table_new(TAB_COLS, applications.size(), true);
  GtkWidget *installButton = NULL;
  GtkWidget *runButton = NULL;
  GtkWidget *label = NULL;

  //Set some parameters
  gtk_window_set_title (GTK_WINDOW (win), _("GtkInstaller"));
  gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  gtk_widget_realize (win);
  gtk_table_set_row_spacings(GTK_TABLE(table), 2);
  gtk_table_set_col_spacings(GTK_TABLE(table), 2);
  g_signal_connect (win, "destroy", gtk_main_quit, NULL);

  //Init fixed container and add widgets
  for(unsigned short i=1;i<=applications.size();i++)
    {
        struct stat statBuf;
        installButton = gtk_button_new_with_label(_("Installieren"));
        label = gtk_label_new(_(applications[i-1].name.c_str()));
        g_print(applications[i-1].statF.c_str());
        if(stat(applications[i-1].statF.c_str(), &statBuf)==0)
            {gtk_widget_set_sensitive(installButton,FALSE);}
        gtk_signal_connect(GTK_OBJECT(installButton), "clicked", GTK_SIGNAL_FUNC(processClick), GINT_TO_POINTER(i-1));
        gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, i, i+1);
        gtk_table_attach_defaults(GTK_TABLE(table), installButton, 1, 2, i, i+1);
    }

  /* Enter the main loop */
  gtk_container_add (GTK_CONTAINER (win), table);
  gtk_widget_show_all (win);
  gtk_main ();
  return 0;
}
