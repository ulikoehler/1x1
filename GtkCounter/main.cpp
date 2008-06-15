#include <stdlib.h>
#include <gtk/gtk.h>
#include <libintl.h>
#include <boost/lexical_cast.hpp>
#include <map>
#include "counterWindow.h"
using namespace std;
using namespace boost;

///Global variables
sqlite3 *db;
#define DBFILENAME "~/.gtkcounter.db"


static void openCounter(GtkWidget *wid, gpointer data)
{
    createCounterWindow("test");
}


int main (int argc, char *argv[])
{
  gtk_init (&argc, &argv); //Init GTK
  GtkWidget *button = NULL;
  GtkWidget *win = NULL;
  GtkWidget *vbox = NULL;

  struct stat statbuf;

  //Create sqlite database if not exists and open connection
  if(stat(DBFILENAME,&statbuf)!=0)
      {
          system(strcat("sqlite3 ", DBFILENAME));
      }
  sqlite3_open(DBFILENAME, &db);

  /* Create the main window */
  win = gtk_window_new (GTK_WINDOW_TOPLEVEL);
  gtk_container_set_border_width (GTK_CONTAINER (win), 8);
  gtk_window_set_title (GTK_WINDOW (win), "GtkCounter");
  gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  gtk_widget_realize (win);
  g_signal_connect (win, "destroy", gtk_main_quit, NULL);

  /* Create a vertical box with buttons */
  vbox = gtk_vbox_new (TRUE, 6);
  gtk_container_add (GTK_CONTAINER (win), vbox);

  button = gtk_button_new_from_stock (GTK_STOCK_DIALOG_INFO);
  g_signal_connect (G_OBJECT (button), "clicked", G_CALLBACK (openCounter), (gpointer) win);
  gtk_box_pack_start (GTK_BOX (vbox), button, TRUE, TRUE, 0);

  button = gtk_button_new_from_stock (GTK_STOCK_CLOSE);
  g_signal_connect (button, "clicked", gtk_main_quit, NULL);
  gtk_box_pack_start (GTK_BOX (vbox), button, TRUE, TRUE, 0);

  /* Enter the main loop */
  gtk_widget_show_all (win);
  gtk_main ();
  //Close Sqlite database connection
  sqlite3_close(db);
  return 0;
}
