#include <stdio.h>
#include <cmath>
#include <gtk/gtk.h>
#include <libintl.h>
#include <list>
#include <iostream>
#include <algorithm>
#include <boost/lexical_cast.hpp>
using namespace boost;
using namespace std;
#define _(x) gettext(x)
#define root(x,n) pow(x, 1.0/n)

///Typedefs/typedef-like defines


//Main window widgets
GtkWidget *win;
GtkWidget *vbox;
GtkWidget *hbox;
GtkWidget *label;
GtkWidget *addEntry;
GtkWidget *weightSpinButton;
GtkWidget *addButton;
GtkWidget *datasetLabel; //Shows how many datasets have been entered already
GtkWidget *clearButton;
GtkWidget *showButton;
GtkWidget *algorithmComboBox;
GtkWidget *nSpinButton;
GtkWidget *averageLabel;

GtkWidget *messageDialog;

//List window widgets
GtkWidget *listWindow;
GtkWidget *listMainHbox;
GtkWidget *listView;
GtkWidget *listUtilsVbox;
GtkWidget *listDeleteButton;

list<pair<double,float> > nums;

///Function prototypes
static void updateMainWindow(void);
static void deleteDataset(GtkWidget *wid, gpointer data);
static void hideListWindow(void);
static void showListWindow(void);
static void updateListWindow(void);
static void clearData(void);
static void addNumber(void);

///Update data in GUI, called especially to show the mean value
///+If the user has selected power mean, active nSpinButton, else deactivate it
static void updateMainWindow(void)
{
    unsigned int size = nums.size();
    long double mean = 0.0;
    long double weightSum = 0.0;
    std::list<std::pair<double, float> >::iterator it = nums.begin();
    switch(gtk_combo_box_get_active(GTK_COMBO_BOX(algorithmComboBox)))
    {
        case 0: //Arithmetic
            {
                while(it != nums.end())
                {
                    mean += it->first;; //Sum up first pair value (= actual value)
                    weightSum += it->second; //Sum up second pair value (= weight)
                    it++; //Select next element in container referred by iterator
                }
                mean /= weightSum;
                gtk_widget_set_sensitive(nSpinButton, false); //Deactive nSpinButton
                break;
            }
        case 1: //Geometric
            {
                mean = 1.0;
                while(it != nums.end())
                {
                    mean *= it->first;; //Multiply up first pair value (= actual value)
                    weightSum += it->second;; //Sum up second pair value (= weight)
                    it++; //Select next element in container referred by iterator
                }
                mean = root(mean, weightSum);
                gtk_widget_set_sensitive(nSpinButton, false); //Deactive nSpinButton
                break;
            }
        case 2: //Harmonic
            {
                while(it != nums.end())
                {
                    mean += 1.0/ it->first;
                    it++; //Select next element in container referred by iterator
                }
                mean = size/mean;
                gtk_widget_set_sensitive(nSpinButton, false); //Deactive nSpinButton
                break;
            }
        case 3: //Power
            {
                static int n = floor(gtk_spin_button_get_value(GTK_SPIN_BUTTON(nSpinButton)));
                while(it != nums.end())
                {
                    mean += pow(it->first, n);
                    it++; //Select next element in container referred by iterator
                }
                mean /= size;
                mean = root(mean, n);
                gtk_widget_set_sensitive(nSpinButton, true); //Deactive nSpinButton
                break;
            }
        default: break;
    }
    gtk_label_set_text(GTK_LABEL(datasetLabel), lexical_cast<string>(size).c_str());
    if(size != 0)
        {
            gtk_label_set_text(GTK_LABEL(averageLabel), lexical_cast<string>(mean).c_str());
        }
}

///Delete dataset, callback function used by showData(); arguments (in data): index of dataset to delete
static void deleteDataset(GtkWidget *wid, gpointer data)
{

}

///Hide list window
static void hideListWindow(void)
{
    gtk_widget_hide(listWindow);
}

static void initListWindow()
{
    listWindow = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    listMainHbox = gtk_hbox_new(false, 8);
    listView = gtk_tree_view_new();

    GtkCellRenderer *valueRenderer = gtk_cell_renderer_text_new();
    GtkCellRenderer *weightRenderer = gtk_cell_renderer_text_new();

    gtk_tree_view_insert_column_with_attributes (GTK_TREE_VIEW(listView), -1, _("Value"), valueRenderer, "text", NULL);
    gtk_tree_view_insert_column_with_attributes (GTK_TREE_VIEW(listView), -1, _("Weight"), weightRenderer, "text", NULL);

    updateListWindow();

    gtk_box_pack_start_defaults(GTK_BOX(listMainHbox), listView);
    gtk_container_add (GTK_CONTAINER(listWindow), listMainHbox);
}

///Show window with datasets and buttons to delete single values
static void showListWindow(void)
{
    gtk_widget_show(listWindow);
}

///Updates the list in the main window.
static void updateListWindow()
{
    GtkListStore *store;
    GtkTreeIter iter;

    gtk_tree_view_set_model (GTK_TREE_VIEW (listView), NULL);

    store = gtk_list_store_new(2, G_TYPE_DOUBLE, G_TYPE_FLOAT);

    //Fill treeview with data
    std::list<std::pair<double, float> >::iterator it = nums.begin();
    while(it != nums.end())
        {
            gtk_list_store_append (store, &iter);
            gtk_list_store_set (store, &iter, 0, it->first, 1, it->second, -1);
            it++; //Select next element in container referred by iterator
        }
    gtk_tree_view_set_model (GTK_TREE_VIEW (listView), GTK_TREE_MODEL(store));
}
///Remove all data from the container
static void clearData(void)
{
    nums.clear();
    gtk_label_set_text(GTK_LABEL(datasetLabel), "0");
    gtk_label_set_text(GTK_LABEL(averageLabel), "");
}

///Inserts a single value in the vector
static void addNumber(void)
{
    string value(gtk_entry_get_text(GTK_ENTRY(addEntry)));
    static string::size_type v = value.find(",", 0);
    if(v != string::npos)
        {
            value[v] = '.';
            gtk_entry_set_text(GTK_ENTRY(addEntry), value.c_str());
        }
    try
        {
            nums.push_back(std::make_pair(lexical_cast<double>(value), gtk_spin_button_get_value_as_float(GTK_SPIN_BUTTON(weightSpinButton))));
        }
    catch(bad_lexical_cast &)
        {
            messageDialog = gtk_message_dialog_new (GTK_WINDOW(win), GTK_DIALOG_DESTROY_WITH_PARENT, GTK_MESSAGE_ERROR, GTK_BUTTONS_CLOSE, _("Incorrect value!"));
            gtk_dialog_run(GTK_DIALOG (messageDialog));
            gtk_widget_destroy(messageDialog);
        }
    gtk_widget_grab_focus(addEntry);
    updateMainWindow();
    updateListWindow();
}

int main (int argc, char *argv[])
{
  gtk_init (&argc, &argv);
  initListWindow();
  /* Initialize GTK+ */
  g_log_set_handler ("Gtk", G_LOG_LEVEL_WARNING, (GLogFunc) gtk_false, NULL);
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
  label = gtk_label_new(_("Value:"));
   gtk_box_pack_start_defaults(GTK_BOX(hbox), label);
  addEntry = gtk_entry_new();
   g_signal_connect(addEntry, "activate", addNumber, NULL);
   gtk_box_pack_start_defaults(GTK_BOX(hbox), addEntry);
  label = gtk_label_new(_("Weight:"));
   gtk_box_pack_start_defaults(GTK_BOX(hbox), label);
  weightSpinButton = gtk_spin_button_new_with_range(0.001, 999.0, 0.1);
   gtk_spin_button_set_value(GTK_SPIN_BUTTON(weightSpinButton), 1.0f);
   gtk_box_pack_start_defaults(GTK_BOX(hbox), weightSpinButton);
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
  showButton = gtk_button_new_with_label(_("Show"));
   g_signal_connect (showButton, "clicked", showListWindow, NULL);
   gtk_box_pack_start_defaults(GTK_BOX(hbox), showButton);
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
   g_signal_connect (algorithmComboBox, "changed", updateMainWindow, NULL);
   gtk_box_pack_start_defaults(GTK_BOX(hbox), algorithmComboBox);
  label = gtk_label_new(_("n:"));
   gtk_box_pack_start_defaults(GTK_BOX(hbox), label);
  nSpinButton = gtk_spin_button_new_with_range(2, 999999, 1.0);
   gtk_spin_button_set_value(GTK_SPIN_BUTTON(nSpinButton), 2);
   g_signal_connect (nSpinButton, "change-value", updateMainWindow, NULL);
   gtk_box_pack_start_defaults(GTK_BOX(hbox), nSpinButton);
  //Result row (or in same row ifndef MEAN_NEW_ROW
  #ifdef MEAN_NEW_ROW
   gtk_box_pack_start_defaults(GTK_BOX(vbox), hbox);
   hbox = gtk_hbox_new(false, 5);
  #endif
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
