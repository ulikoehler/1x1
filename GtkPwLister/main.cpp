#include <stdlib.h>
#include <gtk/gtk.h>
#include <libintl.h>
#include <openssl/rand.h>
#include <string>
#include <vector>
#include <boost/foreach.hpp>
#include <boost/lexical_cast.hpp>
using namespace std;
///Macros and defines
#define _(x) gettext(x)

///Global variables
GtkWidget *win;
GtkWidget *table;
GtkWidget *label; //Generic usage :-)
GtkWidget *masterField;
GtkWidget *lengthSpinButton;
GtkWidget *outputHbox;
GtkWidget *fileEntry;
GtkWidget *fileTypeComboBox;
GtkWidget *okButton;

vector<string> pws;

static void okButtonClicked(GtkWidget *wid, gpointer data);
void showList();

static void okButtonClicked(GtkWidget *wid, gpointer data)
{
    int length = gtk_spin_button_get_value_as_int(GTK_SPIN_BUTTON(lengthSpinButton));
    void* bufferSpace = malloc((length+1) * sizeof(char));
    static unsigned char *pwBuffer = (unsigned char*)bufferSpace;
    static string master(gtk_entry_get_text(GTK_ENTRY(masterField)));
    RAND_seed(master.c_str(), master.length());
    for (int i = 0; i < length;i++)
        {
            RAND_bytes(pwBuffer, length*sizeof(char));
            string sBuf((const char*)pwBuffer, length);
            pws.push_back(sBuf);
        }
    free(bufferSpace);
    showList();
    RAND_cleanup();
}

void showList()
{
    GtkWidget *listWindow;
    GtkWidget *hbox;
    GtkWidget *treeView;
    GtkListStore *passwords;
    GtkTreeIter iter;
    listWindow = gtk_window_new(GTK_WINDOW_TOPLEVEL);
     gtk_window_set_title(GTK_WINDOW(win), _("Passwords"));
     gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
     gtk_window_set_resizable(GTK_WINDOW(win), false);
     g_signal_connect (win, "destroy", gtk_main_quit, NULL);
     gtk_widget_realize (win);
    hbox = gtk_hbox_new(false, 3);
    treeView = gtk_tree_view_new();
    passwords = gtk_list_store_new(1, G_TYPE_STRING);
    ///Fill passwords list
    BOOST_FOREACH(string s, pws)
        {
            gtk_list_store_set(passwords, &iter, 0, s.c_str());
        }
    gtk_tree_view_set_model(GTK_TREE_VIEW(treeView), GTK_TREE_MODEL(passwords));
    gtk_container_add(GTK_CONTAINER(listWindow), hbox);
    gtk_widget_show_all(listWindow);
}

//static char** calculatePasswords(char* input, long length){return  NULL;}
int main (int argc, char *argv[])
{
  gtk_init (&argc, &argv);

  win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
  table = gtk_table_new(0, 0, false);

  //Set some parameters
  gtk_window_set_title (GTK_WINDOW (win), _("GtkPwLister"));
  gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  gtk_window_set_resizable(GTK_WINDOW(win), false);
  gtk_widget_realize (win);
  gtk_table_set_row_spacings(GTK_TABLE(table), 3);
  gtk_table_set_col_spacings(GTK_TABLE(table), 3);
  g_signal_connect (win, "destroy", gtk_main_quit, NULL);

  ///Init main widgets
  label = gtk_label_new(_("Master password:"));
    gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 0, 1);
  masterField  = gtk_entry_new();
    gtk_table_attach_defaults(GTK_TABLE(table), masterField, 1, 2, 0, 1);
  label = gtk_label_new(_("Length"));
    gtk_table_attach_defaults(GTK_TABLE(table), label, 1, 2, 1, 2);
  lengthSpinButton = gtk_spin_button_new_with_range(1.0, 20.0, 1.0);
    gtk_spin_button_set_value(GTK_SPIN_BUTTON(lengthSpinButton), 6.0);
    gtk_table_attach_defaults(GTK_TABLE(table), lengthSpinButton, 1, 2, 1, 2);
  label = gtk_label_new(_("Output:"));
    gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 2, 3);
  outputHbox = gtk_hbox_new(false, 3);
    fileEntry = gtk_entry_new();
        gtk_box_pack_start_defaults(GTK_BOX(outputHbox), fileEntry);
    fileTypeComboBox = gtk_combo_box_new_text();
        gtk_combo_box_append_text(GTK_COMBO_BOX(fileTypeComboBox), _("Show"));
        gtk_combo_box_append_text(GTK_COMBO_BOX(fileTypeComboBox), _("Save"));
        gtk_combo_box_append_text(GTK_COMBO_BOX(fileTypeComboBox), _("PDF"));
        gtk_combo_box_set_active(GTK_COMBO_BOX(fileTypeComboBox), 0);
        gtk_box_pack_start_defaults(GTK_BOX(outputHbox), fileTypeComboBox);
    gtk_table_attach_defaults(GTK_TABLE(table), outputHbox, 1, 2, 2, 3);
  okButton = gtk_button_new_with_label(_("OK"));
    gtk_table_attach_defaults(GTK_TABLE(table), okButton, 0, 2, 3, 4);
    g_signal_connect (okButton, "clicked", G_CALLBACK(okButtonClicked), NULL);

  /* Enter the main loop */
  gtk_container_add (GTK_CONTAINER (win), table);
  gtk_widget_show_all(win);
  gtk_main ();
  return 0;
}
