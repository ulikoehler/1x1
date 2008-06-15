#include <libintl.h>
#include <gtk/gtk.h>
#define _(x) gettext(x)

using namespace std;

void showMainWindow();
extern void showCertWindow(GtkWidget *wid, gpointer data);
extern void showGenerateKeyWindow(GtkWidget *wid, gpointer data);
