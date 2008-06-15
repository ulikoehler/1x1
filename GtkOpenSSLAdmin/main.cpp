#include "gui.h"
#include "libcryptowrapper.h"

int main (int argc, char *argv[])
{
    //Init Gtk
    gtk_init (&argc, &argv);
    showMainWindow();
    gtk_main ();
    return 0;
}
