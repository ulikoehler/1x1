#include <gtk/gtk.h>
#include "../../gui.h"

void showGenerateKeyWindow(GtkWidget *wid, gpointer data)
{
  GtkWidget *win;
  GtkWidget *table;
  GtkWidget *keysizeComboBox;
  GtkWidget *label; //Used for all labels
  GtkWidget *encryptionHbox;
   GtkWidget *aesRadioButton;
   GtkWidget *des3RadioButton;
   GtkWidget *noEncryptionRadioButton;

  gtk_window_set_title (GTK_WINDOW (win), _("Generate Key"));
  gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  gtk_widget_realize (win);
  gtk_table_set_row_spacings(GTK_TABLE(table), 3);
  gtk_table_set_col_spacings(GTK_TABLE(table), 3);
  g_signal_connect (win, "destroy", gtk_main_quit, NULL);

  ///Init main widgets
  label = gtk_label_new(_("KeySize:"));
   gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 0, 2);
  noEncryptionRadioButton = gtk_radio_button_new_with_label(NULL, _("None"));
  aesRadioButton = gtk_radio_button_new_with_label(gtk_radio_button_group(GTK_RADIO_BUTTON(noEncryptionRadioButton)), _("AES"));
  des3RadioButton = gtk_radio_button_new_with_label(gtk_radio_button_group(GTK_RADIO_BUTTON(aesRadioButton)), _("Triple DES"));
   gtk_widget_show_all(encryptionHbox);
   gtk_table_attach_defaults(GTK_TABLE(table), encryptionHbox, 1, 2, 3, 4);

  /* Enter the main loop */
  gtk_container_add (GTK_CONTAINER (win), table);
  gtk_widget_show_all(win);
}
