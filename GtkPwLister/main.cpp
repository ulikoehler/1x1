#include <stdlib.h>
#include <gtk/gtk.h>
#include <libintl.h>
#include <openssl/rand.h>
#include <string>
#include <vector>
#include <fstream>
#include <boost/foreach.hpp>
#include <boost/lexical_cast.hpp>
#include "pwgen.h"
using namespace std;
using namespace boost;
///Macros and defines
#define _(x) gettext(x)

///pwgen initialization
int (*pw_number)(int max_num);

///Global variables
GtkWidget *win;
GtkWidget *table;
GtkWidget *label;
GtkWidget *hbox;
GtkWidget *lengthSpinButton;
//Capitalize radiobuttons
GtkWidget *capNoneRb;
GtkWidget *capOnRb;
GtkWidget *capOffRb;
//Numerals radiobuttons
GtkWidget *numNoneRb;
GtkWidget *numOnRb;
GtkWidget *numOffRb;
//Other option widget
GtkWidget *symbolsCb;
GtkWidget *secureCb;
GtkWidget *vowelsOffCb;

GtkWidget *okButton;

int	pw_length = 8;
int	num_pw = -1;
int	pwgen_flags = 0;
int	do_columns = 0;

vector<string> pws;

static void okButtonClicked(GtkWidget *wid, gpointer data);
void showList();
void saveList();

static void okButtonClicked(GtkWidget *wid, gpointer data)
{
    pw_length = gtk_spin_button_get_value_as_int(GTK_SPIN_BUTTON(lengthSpinButton));

}

void showList()
{
    GtkWidget *listWindow;
    GtkWidget *layout;
    GtkWidget *vbox;
    GtkListStore *passwords;
    //Label is inlined
    listWindow = gtk_window_new(GTK_WINDOW_TOPLEVEL);
     gtk_window_set_title(GTK_WINDOW(win), _("Passwords"));
     gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
     gtk_window_set_resizable(GTK_WINDOW(win), false);
     g_signal_connect (win, "destroy", gtk_main_quit, NULL);
     gtk_widget_realize (win);
    layout = gtk_layout_new(NULL, NULL);
        gtk_layout_set_size(GTK_LAYOUT(layout), 10*gtk_spin_button_get_value_as_int(GTK_SPIN_BUTTON(lengthSpinButton)), 200);
    vbox = gtk_vbox_new(true, 1);
        gtk_container_add(GTK_CONTAINER(layout), vbox);
    passwords = gtk_list_store_new(1, G_TYPE_STRING);
    ///Fill passwords list
    BOOST_FOREACH(string s, pws)
        {
            gtk_box_pack_start_defaults(GTK_BOX(vbox), gtk_label_new(s.c_str()));
        }
    gtk_container_add(GTK_CONTAINER(listWindow), layout);
    gtk_widget_show_all(listWindow);
    gtk_window_resize(GTK_WINDOW(win), 400, 400);
}

void saveListPdf()
{

}

//static char** calculatePasswords(char* input, long length){return  NULL;}
int main (int argc, char *argv[])
{
  gtk_init (&argc, &argv);

  win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
  table = gtk_table_new(0, 0, false);

  //Set some parameters
  gtk_window_set_title (GTK_WINDOW (win), _("GtkPwLister"));
  gtk_container_set_border_width (GTK_CONTAINER (win), 3);
  gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  gtk_window_resize(GTK_WINDOW(win), 0, 0);
  gtk_window_set_resizable(GTK_WINDOW(win), false);
  gtk_widget_realize (win);
  gtk_table_set_row_spacings(GTK_TABLE(table), 3);
  gtk_table_set_col_spacings(GTK_TABLE(table), 3);
  g_signal_connect (win, "destroy", gtk_main_quit, NULL);

  ///Init widgets
  label = gtk_label_new(_("Length:"));
    gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 0, 1);
  lengthSpinButton = gtk_spin_button_new_with_range(1.0, 60.0, 1.0);
    gtk_spin_button_set_value(GTK_SPIN_BUTTON(lengthSpinButton), 6.0);
    gtk_table_attach_defaults(GTK_TABLE(table), lengthSpinButton, 1, 2, 0, 1);
  ////Capitals
  label = gtk_label_new(_("Capitals:"));
    gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 1, 2);
  hbox = gtk_hbox_new(false, 3);
    capNoneRb = gtk_radio_button_new_with_label(NULL, _("Random"));
     gtk_box_pack_start_defaults(GTK_BOX(hbox), capNoneRb);
     gtk_toggle_button_set_active(GTK_TOGGLE_BUTTON(capNoneRb), true);
    capOnRb = gtk_radio_button_new_with_label(gtk_radio_button_group(GTK_RADIO_BUTTON(capNoneRb)), _("At least one"));
     gtk_box_pack_start_defaults(GTK_BOX(hbox), capOnRb);
    capOffRb = gtk_radio_button_new_with_label(gtk_radio_button_group(GTK_RADIO_BUTTON(capOnRb)), _("None"));
     gtk_box_pack_start_defaults(GTK_BOX(hbox), capOffRb);
  gtk_table_attach_defaults(GTK_TABLE(table), hbox, 1, 2, 1, 2);
  ////Numerals
  label = gtk_label_new(_("Numerals:"));
    gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 2, 3);
  hbox = gtk_hbox_new(false, 3);
    numNoneRb = gtk_radio_button_new_with_label(NULL, _("Random"));
     gtk_box_pack_start_defaults(GTK_BOX(hbox), numNoneRb);
     gtk_toggle_button_set_active(GTK_TOGGLE_BUTTON(numNoneRb), true);
    numOnRb = gtk_radio_button_new_with_label(gtk_radio_button_group(GTK_RADIO_BUTTON(numNoneRb)), _("At least one"));
     gtk_box_pack_start_defaults(GTK_BOX(hbox), numOnRb);
    numOffRb = gtk_radio_button_new_with_label(gtk_radio_button_group(GTK_RADIO_BUTTON(numOnRb)), _("None"));
     gtk_box_pack_start_defaults(GTK_BOX(hbox), numOffRb);
  gtk_table_attach_defaults(GTK_TABLE(table), hbox, 1, 2, 2, 3);
  ////Other options
  label = gtk_label_new(_("Other Options:"));
    gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 3, 4);
  hbox = gtk_hbox_new(false, 3);
    symbolsCb = gtk_check_button_new_with_label(_("Symbols"));
     gtk_box_pack_start_defaults(GTK_BOX(hbox), symbolsCb);
    secureCb = gtk_check_button_new_with_label(_("Secure"));
     gtk_toggle_button_set_active(GTK_TOGGLE_BUTTON(secureCb), true);
     gtk_box_pack_start_defaults(GTK_BOX(hbox), secureCb);
    vowelsOffCb = gtk_check_button_new_with_label(_("No vowels"));
     gtk_box_pack_start_defaults(GTK_BOX(hbox), vowelsOffCb);
  gtk_table_attach_defaults(GTK_TABLE(table), hbox, 1, 2, 3, 4);
  ////OK Button
  okButton = gtk_button_new_with_label(_("OK"));
    gtk_table_attach_defaults(GTK_TABLE(table), okButton, 0, 2, 4, 5);
    g_signal_connect (okButton, "clicked", G_CALLBACK(okButtonClicked), NULL);
  ///End init widgets

  /* Enter the main loop */
  gtk_container_add (GTK_CONTAINER (win), table);
  gtk_widget_show_all(win);
  gtk_main ();
  return 0;
}
