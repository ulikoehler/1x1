#include <stdlib.h>
#include <gtk/gtk.h>
#include <libintl.h>
#include <openssl/rand.h>
#include <string>
#include <vector>
#include <fstream>
#include <boost/foreach.hpp>
#include <boost/lexical_cast.hpp>
using namespace std;
using namespace boost;
///Macros and defines
#define _(x) gettext(x)
///Define appropriate field value of outputEntry
#ifdef linux
    #define OUTPUT_PRESET "/dev/shm/passwords.txt"
#else
    #define OUTPUT_PRESET "./passwords.txt"
#endif


///Global variables
GtkWidget *win;
GtkWidget *table;
GtkWidget *label; //Generic usage :-)
GtkWidget *masterField;
GtkWidget *lengthSpinButton;
GtkWidget *outputHbox;
GtkWidget *outputEntry;
GtkWidget *outputTypeComboBox;
GtkWidget *okButton;

vector<string> pws;

static void okButtonClicked(GtkWidget *wid, gpointer data);
void showList();
void saveList();

static void okButtonClicked(GtkWidget *wid, gpointer data)
{
    int length = gtk_spin_button_get_value_as_int(GTK_SPIN_BUTTON(lengthSpinButton));
    static int decisionInt; ///Decides whether to use numbers oder (capital) letters
    static unsigned char pwChar;
    static string sbuf = "";
    static string master(gtk_entry_get_text(GTK_ENTRY(masterField)));
    RAND_seed(master.c_str(), master.length());
    for (int i = 0; i < length;i++)
        {
            for(int j = 0;j < length; j++)
                {
                    RAND_pseudo_bytes(&pwChar, 1);
                    RAND_pseudo_bytes(reinterpret_cast<unsigned char*>(&decisionInt), 1); //Fill decisionInt with random data
                    switch(decisionInt % 3)
                    {
                        case 0: {pwChar = (pwChar % 11) + 48;break;} ///Number
                        case 1: {pwChar = (pwChar % 26) + 65;break;} ///Uppercase letter
                        case 2: {pwChar = (pwChar % 26) + 97;break;} ///Lowercase letter
                        default: break;
                    }
                    sbuf.append(1, pwChar);
                }
            pws.push_back(sbuf);
            sbuf.clear();
        }
    ///Now do what the user has chosen, e.g. showing or saving the list
    switch(gtk_combo_box_get_active(GTK_COMBO_BOX(outputTypeComboBox)))
        {
            case 0: {showList();break;} ///Show
            case 1: {saveList();break;} ///Save to text file
            case 2: {break;} ///TODO: Save to PDF
            default: break;
        }
    ///Cleanup
    pws.clear();
    RAND_cleanup();
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
}

void saveList()
{
    fstream f(gtk_entry_get_text(GTK_ENTRY(outputEntry)), fstream::out);
    BOOST_FOREACH(string s, pws)
        {
            f << s << "\n";
        }
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
  lengthSpinButton = gtk_spin_button_new_with_range(1.0, 60.0, 1.0);
    gtk_spin_button_set_value(GTK_SPIN_BUTTON(lengthSpinButton), 6.0);
    gtk_table_attach_defaults(GTK_TABLE(table), lengthSpinButton, 1, 2, 1, 2);
  label = gtk_label_new(_("Output:"));
    gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 2, 3);
  outputHbox = gtk_hbox_new(false, 3);
    outputEntry = gtk_entry_new();
        gtk_entry_set_text(GTK_ENTRY(outputEntry), OUTPUT_PRESET);
        gtk_box_pack_start_defaults(GTK_BOX(outputHbox), outputEntry);
    outputTypeComboBox = gtk_combo_box_new_text();
        gtk_combo_box_append_text(GTK_COMBO_BOX(outputTypeComboBox), _("Show"));
        gtk_combo_box_append_text(GTK_COMBO_BOX(outputTypeComboBox), _("Save"));
        gtk_combo_box_append_text(GTK_COMBO_BOX(outputTypeComboBox), _("PDF"));
        gtk_combo_box_set_active(GTK_COMBO_BOX(outputTypeComboBox), 0);
        gtk_box_pack_start_defaults(GTK_BOX(outputHbox), outputTypeComboBox);
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
