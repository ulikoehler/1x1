#include <stdlib.h>
#include <gtk/gtk.h>
#include <libintl.h>
using namespace std;
///Macros and defines
#define _(x) gettext(x)
#define NUM_PWS 60
#define PWS_PER_PAGE NUM_PWS/2

///Global variables
GtkWidget *win;
GtkWidget *table;
GtkWidget *masterLabel;
GtkWidget *masterField;
GtkWidget *lengthLabel;
GtkWidget *lengthSpinButton;
GtkWidget *okButton;

static void helloWorld (GtkWidget *wid, GtkWidget *win)
{
}

//static char** calculatePasswords(char* input, long length){return  NULL;}
int main (int argc, char *argv[])
{
  gtk_init (&argc, &argv);

  win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
  table = gtk_table_new(0, 0, true);

  //Set some parameters
  gtk_window_set_title (GTK_WINDOW (win), _("GtkPwLister"));
  gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  gtk_widget_realize (win);
  gtk_table_set_row_spacings(GTK_TABLE(table), 3);
  gtk_table_set_col_spacings(GTK_TABLE(table), 3);
  g_signal_connect (win, "destroy", gtk_main_quit, NULL);

  ///Init main widgets
  masterLabel = gtk_label_new(_("Master password:"));
    gtk_table_attach_defaults(GTK_TABLE(table), masterLabel, 0, 1, 0, 1);
  masterField  = gtk_entry_new();
    gtk_table_attach_defaults(GTK_TABLE(table), masterField, 1, 2, 0, 1);
  lengthLabel = gtk_label_new(_("Length"));
    gtk_table_attach_defaults(GTK_TABLE(table), lengthLabel, 1, 0, 1, 2);
  lengthSpinButton = gtk_spin_button_new_with_range(1.0, 20.0, 1.0);
    gtk_spin_button_set_value(GTK_SPIN_BUTTON(lengthSpinButton), 6.0);
    gtk_table_attach_defaults(GTK_TABLE(table), lengthSpinButton, 1, 2, 1, 2);


  /* Enter the main loop */
  gtk_container_add (GTK_CONTAINER (win), table);
  gtk_widget_show_all(win);
  gtk_main ();
  return 0;
}
