#include <stdio.h>
#include <gtk/gtk.h>
#include <libintl.h>
#include <vector>
#include <boost/lexical_cast.hpp>
#include <boost/foreach.hpp>
using namespace boost;
using namespace std;
#define _(x) gettext(x)

GtkWidget *win;
GtkWidget *vbox;
GtkWidget *hbox;
GtkWidget *label;
GtkWidget *addEntry;
GtkWidget *addButton;
GtkWidget *datasetLabel; //Shows how many datasets have been entered already
GtkWidget *clearButton;
GtkWidget *algorithmComboBox;
GtkWidget *nEntry;
GtkWidget *averageLabel;

vector<long double> nums;


static void update(void)
{
    unsigned int size = nums.size();
    long double mean = 0.0;
    switch(gtk_combo_box_get_active(GTK_COMBO_BOX(algorithmComboBox)))
    {
        case 0: //Arithmetic
            {
                BOOST_FOREACH(long double e, nums)
                {
                    mean += e; //Directly using mean variable
                }
                mean /= size;
                break;
            }
        case 1: //Geometric
            {
                break;
            }
        case 2: //Harmonic
            {
                break;
            }
        case 3: //Power
            {
                break;
            }
        default: break;
    }
    gtk_label_set_text(GTK_LABEL(datasetLabel), lexical_cast<string>(size).c_str());
    gtk_label_set_text(GTK_LABEL(averageLabel), lexical_cast<string>(mean).c_str());
}

static void clearData(void)
{
    nums.clear();
    gtk_label_set_text(GTK_LABEL(datasetLabel), "0");
    gtk_label_set_text(GTK_LABEL(averageLabel), "");
}

static void processAlgorithmChange(void)
{
    switch(gtk_combo_box_get_active(GTK_COMBO_BOX(algorithmComboBox)))
    {
        case 3: //Power mean
            {
                gtk_entry_set_editable(GTK_ENTRY(nEntry), true);
                break;
            }
        default: //Others
            {
                gtk_entry_set_editable(GTK_ENTRY(nEntry), false);
                break;
            }
    }
    update();
}

static void addNumber(void)
{
    nums.push_back(lexical_cast<long double>(gtk_entry_get_text(GTK_ENTRY(addEntry))));
    update();
}

int main (int argc, char *argv[])
{
  /* Initialize GTK+ */
  g_log_set_handler ("Gtk", G_LOG_LEVEL_WARNING, (GLogFunc) gtk_false, NULL);
  gtk_init (&argc, &argv);
  g_log_set_handler ("Gtk", G_LOG_LEVEL_WARNING, g_log_default_handler, NULL);

  /* Create the main window */
  win = gtk_window_new (GTK_WINDOW_TOPLEVEL);
  gtk_container_set_border_width (GTK_CONTAINER (win), 8);
  gtk_window_set_title (GTK_WINDOW (win), _("Averator"));
  gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  g_signal_connect (win, "destroy", gtk_main_quit, NULL);

  /* Create a vertical box with buttons */
  vbox = gtk_vbox_new (false, 5);
  gtk_container_add (GTK_CONTAINER (win), vbox);
  ///Add main widgets
  //Row to add numbers
  hbox = gtk_hbox_new(false, 5);
  addEntry = gtk_entry_new();
   gtk_box_pack_start_defaults(GTK_BOX(hbox), addEntry);
  addButton = gtk_button_new_with_label(_("Add"));
   g_signal_connect (addButton, "clicked", addNumber, NULL);
   gtk_box_pack_start_defaults(GTK_BOX(hbox), addButton);
  label = gtk_label_new(_("Datasets:"));
   gtk_box_pack_start_defaults(GTK_BOX(hbox), label);
  datasetLabel = gtk_label_new("0");
   gtk_box_pack_start_defaults(GTK_BOX(hbox), datasetLabel);
  clearButton = gtk_button_new_with_label(_("Clear"));
   g_signal_connect (clearButton, "clicked", clearData, NULL);
   gtk_box_pack_start_defaults(GTK_BOX(hbox), clearButton);
  gtk_box_pack_start_defaults(GTK_BOX(vbox), hbox);
  //Row to choose algorithm (and parameters)
  hbox = gtk_hbox_new(false, 5);
  label = gtk_label_new(_("Algorithm:"));
   gtk_box_pack_start_defaults(GTK_BOX(hbox), label);
  algorithmComboBox = gtk_combo_box_new_text();
   gtk_combo_box_append_text(GTK_COMBO_BOX(algorithmComboBox), _("Arithmetic mean"));
   gtk_combo_box_append_text(GTK_COMBO_BOX(algorithmComboBox), _("Geometric mean"));
   gtk_combo_box_append_text(GTK_COMBO_BOX(algorithmComboBox), _("Harmonic mean"));
   gtk_combo_box_append_text(GTK_COMBO_BOX(algorithmComboBox), _("Power mean"));
   gtk_combo_box_set_active(GTK_COMBO_BOX(algorithmComboBox), 0);
   g_signal_connect (algorithmComboBox, "changed", processAlgorithmChange, NULL);
   gtk_box_pack_start_defaults(GTK_BOX(hbox), algorithmComboBox);
  label = gtk_label_new(_("n:"));
   gtk_box_pack_start_defaults(GTK_BOX(hbox), label);
  nEntry = gtk_entry_new();
   gtk_entry_set_text(GTK_ENTRY(nEntry), "2");
   gtk_entry_set_width_chars(GTK_ENTRY(nEntry), 3);
   gtk_entry_set_editable(GTK_ENTRY(nEntry), false);
   gtk_box_pack_start_defaults(GTK_BOX(hbox), nEntry);
  gtk_box_pack_start_defaults(GTK_BOX(vbox), hbox);
  //Result row
  hbox = gtk_hbox_new(false, 5);
  label = gtk_label_new(_("Mean:"));
   gtk_box_pack_start_defaults(GTK_BOX(hbox), label);
  averageLabel = gtk_label_new("");
   gtk_box_pack_start_defaults(GTK_BOX(hbox), averageLabel);
  gtk_box_pack_start_defaults(GTK_BOX(vbox), hbox);

  /* Enter the main loop */
  gtk_widget_show_all(win);
  gtk_main ();
  return 0;
}
