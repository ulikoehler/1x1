#include "../gui.h"
#include <gtk/gtk.h>

void showMainWindow()
{
  GtkWidget *win;
  GtkWidget *hbox;
  GtkWidget *certButton;

  /* Create the main window */
  win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
  gtk_container_set_border_width(GTK_CONTAINER (win), 8);
  gtk_window_set_title(GTK_WINDOW (win), _("GtkOpenSSLAdmin"));
  gtk_window_set_position(GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  gtk_window_set_resizable(GTK_WINDOW(win), false);
  gtk_widget_realize(win);
  g_signal_connect(win, "destroy", gtk_main_quit, NULL);

  /* Create a vertical box with buttons */
  hbox = gtk_hbox_new(TRUE, 6);

  certButton = gtk_button_new_with_label(_("Certificates"));
  gtk_box_pack_start(GTK_BOX (hbox), certButton, TRUE, TRUE, 0);
  g_signal_connect(G_OBJECT(certButton), "clicked", G_CALLBACK(showCertWindow), NULL);

  /* Enter the main loop */
  gtk_container_add (GTK_CONTAINER (win), hbox);
  gtk_widget_show_all (win);
}
