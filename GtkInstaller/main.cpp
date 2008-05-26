#include <stdlib.h>
#include <fstream>
#include <gtk/gtk.h>
#include <sys/stat.h>
#include <vector>
#include <string>
using namespace std;

///Macros
#ifndef NO_GETTEXT
    #define _(x) gettext(x)
    #include </usr/share/gettext/gettext.h>
#else
    #define _(x) x
#endif
#define TAB_COLS 2

///Classes, typedefs

typedef struct
{
    bool enabled;string name, statF, instCmd;
} section;

///Global variables
vector<GtkWidget*> buttons;
vector<section> applications;

static void parseConfig()
{
    string s;
    fstream f;
    f.open("./gtkinst.cfg", fstream::in);
    g_print("R:%d\n",0);
    while(!f.eof())
    {
        f >> s;
        //if(s[1] == ';')
            //{
                section a;
                a.name = s.substr(1,s.length());
                f >> a.statF; //Read file to check existance
                f >> a.instCmd; //Read command to install app
                applications.push_back(a);
            //}
    }
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
  GtkWidget *table = gtk_table_new(TAB_COLS, applications.size(), TRUE);
  GtkWidget *button = NULL;
  GtkWidget *label = NULL;

  /* Create the main window */
  gtk_window_set_title (GTK_WINDOW (win), _("GtkInstaller"));
  gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  gtk_widget_realize (win);
  g_signal_connect (win, "destroy", gtk_main_quit, NULL);

  //Init fixed container and add widgets
  for(unsigned short i=1;i<=applications.size();i++)
    {
        struct stat statBuf;
        button = gtk_button_new_with_label(_("Installieren"));
        label = gtk_label_new(_(applications[i-1].name.c_str()));
        g_print(applications[i-1].statF.c_str());
        if(stat(applications[i-1].statF.c_str(), &statBuf)==0)
            {gtk_widget_set_sensitive(button,FALSE);}
        gtk_signal_connect(GTK_OBJECT(button), "clicked", GTK_SIGNAL_FUNC(processClick), GINT_TO_POINTER(i-1));
        gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, i, i+1);
        gtk_table_attach_defaults(GTK_TABLE(table), button, 1, 2, i, i+1);
    }

  /* Enter the main loop */
  gtk_container_add (GTK_CONTAINER (win), table);
  gtk_widget_show_all (win);
  gtk_main ();
  return 0;
}
