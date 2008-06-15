#include "../gui.h"
#include <gtk/gtk.h>

void showCertWindow(GtkWidget *wid, gpointer data)
{
  GtkWidget *win;
  GtkWidget *hbox;
  GtkWidget *genKeyButton;

  /* Create the main window */
  win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
  gtk_container_set_border_width(GTK_CONTAINER (win), 8);
  gtk_window_set_title(GTK_WINDOW (win), _("Certificate Administration"));
  gtk_window_set_position(GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  gtk_window_set_resizable(GTK_WINDOW(win), false);
  gtk_widget_realize(win);
  g_signal_connect(win, "destroy", gtk_main_quit, NULL);

  /* Create a vertical box with buttons */
  hbox = gtk_hbox_new(TRUE, 6);

  genKeyButton = gtk_button_new_with_label(_("Generate Key"));
  gtk_box_pack_start(GTK_BOX (hbox), genKeyButton, TRUE, TRUE, 0);
  g_signal_connect(G_OBJECT(genKeyButton), "clicked", G_CALLBACK(showGenerateKeyWindow), NULL);

  /* Enter the main loop */
  gtk_container_add (GTK_CONTAINER (win), hbox);
  gtk_widget_show_all (win);
}
