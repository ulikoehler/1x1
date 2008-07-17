#include <stdlib.h>
#include <gtk/gtk.h>
#include <string>
#include <sys/stat.h>
#include <string.h>
#ifndef NO_GETTEXT
 #include <libintl.h>
#endif
using namespace std;

///Macros and Defines
#ifndef NO_GETTEXT
  #define _(x) gettext(x)
 #else
  #define _(x) x
#endif
#define WINDOW_SPACING 8
#define ROW_SPACING 3
#define COL_SPACING 3
#define TAB_COLS 0
#define TAB_ROWS 0
//Constants for selecting the files via dialog
#define FS_MODE_INPUT 0
#define FS_MODE_OUTPUT 1


///Global variables
//Generic widgets (multiple use pointers
GtkWidget *win;
GtkWidget *table;
GtkWidget *label;
GtkWidget *hbox;
GtkWidget *button;
GtkWidget *dialog;

GtkWidget *inputFilenameEntry;

GtkWidget *outputFilenameEntry;

GtkWidget *passwordEntry;
GtkWidget *passwordVerifyEntry;

GtkWidget *decryptRadioButton;
GtkWidget *encryptRadioButton;

GtkWidget *aesRadioButton;
GtkWidget *twofishRadioButton;
GtkWidget *serpentRadioButton;

GtkWidget *okButton;
//Other variables

///Classes, structs and enums
enum CIPHER
{
		AES = 0,
		TWOFISH = 1,
		SERPENT = 2
};

///FWD declarations
static void cryptFile(const char *inputFilename, const char *outputFilename, const char*password, CIPHER cipher, int mode);

static void openFileButtonClicked(GtkWidget *wid, gpointer data)
{
	switch(GPOINTER_TO_INT(data))
		{
			case FS_MODE_INPUT: {
								dialog = gtk_file_chooser_dialog_new (_("Select output file"),
											       GTK_WINDOW(win),
											      GTK_FILE_CHOOSER_ACTION_OPEN,
											      GTK_STOCK_CANCEL, GTK_RESPONSE_CANCEL,
											      GTK_STOCK_OPEN, GTK_RESPONSE_ACCEPT,
											      NULL);
								if (gtk_dialog_run (GTK_DIALOG (dialog)) == GTK_RESPONSE_ACCEPT)
									{
									    gtk_entry_set_text(GTK_ENTRY(inputFilenameEntry), gtk_file_chooser_get_filename (GTK_FILE_CHOOSER (dialog)));
									}
								break;
							     }
			case FS_MODE_OUTPUT:
							     {
								dialog = gtk_file_chooser_dialog_new ( _("Select output file"),
											      GTK_WINDOW(win),
											      GTK_FILE_CHOOSER_ACTION_SAVE,
											      GTK_STOCK_CANCEL, GTK_RESPONSE_CANCEL,
											      GTK_STOCK_SAVE, GTK_RESPONSE_ACCEPT,
											      NULL);
								if (gtk_dialog_run (GTK_DIALOG (dialog)) == GTK_RESPONSE_ACCEPT)
									{
									    gtk_entry_set_text(GTK_ENTRY(outputFilenameEntry), gtk_file_chooser_get_filename (GTK_FILE_CHOOSER (dialog)));
									}
								break;
							     }
			default: break;
		}
}

static void cryptFile(const char *inputFilename, const char *outputFilename, const char*password, CIPHER cipher, int mode) ///mode: 0=encrypt, 1=decrypt
{
    string cmd = "ncrypt -i ";
    cmd += inputFilename;
    cmd += " -o ";
    cmd += outputFilename;
    cmd += " ";
    switch(cipher)
        {
            case AES: {cmd += "-a ";break;}
            case TWOFISH: {cmd += "-t ";break;}
            case SERPENT: {cmd += "-s ";break;}
            default: break;
        }
    if(mode==0) {cmd += "-e ";}
    else {cmd += "-d ";}
    system(cmd.c_str());
}

static void okButtonClicked(GtkWidget *wid, gpointer data)
{
	//Init variables
	struct stat statbuf;
	char *inputFilename = const_cast<char*>(gtk_entry_get_text(GTK_ENTRY(inputFilenameEntry)));
	char *outputFilename = const_cast<char*>(gtk_entry_get_text(GTK_ENTRY(outputFilenameEntry)));
	char *password = const_cast<char*>(gtk_entry_get_text(GTK_ENTRY(passwordEntry)));
	char *passwordVerification = const_cast<char*>(gtk_entry_get_text(GTK_ENTRY(outputFilenameEntry)));
	///Check if passphrases match
	if(strcmp(password, passwordVerification) != 0)
		{
			dialog = gtk_message_dialog_new (GTK_WINDOW(win),
													  GTK_DIALOG_DESTROY_WITH_PARENT,
													  GTK_MESSAGE_ERROR,
													  GTK_BUTTONS_CLOSE,
													  _("Passwords do not match!"));
			gtk_entry_set_text(GTK_ENTRY(passwordVerifyEntry), "");
			gtk_widget_destroy(dialog);
			return;
		}
	///Check if input file exists, if not show a message and return
	if(!stat(inputFilename, &statbuf))
		{
			 dialog = gtk_message_dialog_new (GTK_WINDOW(win),
												  GTK_DIALOG_DESTROY_WITH_PARENT,
												  GTK_MESSAGE_ERROR,
												  GTK_BUTTONS_CLOSE,
												  _("File does not exist '%s'"),
												  inputFilename);
			gtk_widget_destroy(dialog);
			return;
		}
    ///Check if output file exists, if yes ask the user if we should overwrite it
	if(!stat(outputFilename, &statbuf))
		{
			 dialog = gtk_message_dialog_new (GTK_WINDOW(win),
												  GTK_DIALOG_DESTROY_WITH_PARENT,
												  GTK_MESSAGE_ERROR,
												  GTK_BUTTONS_CLOSE,
												  _("File does not exist '%s'"),
												  inputFilename);
			gtk_widget_destroy(dialog);
			return;
		}
    ///Choose algorithm
    CIPHER cipher;
    if(gtk_toggle_button_get_active(GTK_TOGGLE_BUTTON(aesRadioButton))) {cipher = AES;}
    else if(gtk_toggle_button_get_active(GTK_TOGGLE_BUTTON(twofishRadioButton))) {cipher = TWOFISH;}
    else if(gtk_toggle_button_get_active(GTK_TOGGLE_BUTTON(serpentRadioButton))) {cipher = SERPENT;}
    ///Call encryption function
    cryptFile(gtk_entry_get_text(GTK_ENTRY(inputFilenameEntry)), gtk_entry_get_text(GTK_ENTRY(outputFilenameEntry)), gtk_entry_get_text(GTK_ENTRY(passwordEntry)), cipher, GPOINTER_TO_INT(data));
    gtk_widget_destroy (dialog);
}

int main (int argc, char *argv[])
{
  gtk_init (&argc, &argv);

  win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
  table = gtk_table_new(TAB_ROWS, TAB_COLS, false);

  //Set some parameters
  gtk_window_set_title (GTK_WINDOW (win), _("GtkCrypter"));
  gtk_container_set_border_width (GTK_CONTAINER (win), WINDOW_SPACING);
  gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  gtk_window_set_resizable(GTK_WINDOW (win), false);
  gtk_widget_realize (win);
  gtk_table_set_row_spacings(GTK_TABLE(table), ROW_SPACING);
  gtk_table_set_col_spacings(GTK_TABLE(table), COL_SPACING);
  g_signal_connect (win, "destroy", gtk_main_quit, NULL);

  ///Init main widgets
  //Init input file label and field
  label = gtk_label_new(_("Input file:"));
    gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 0, 1);
  hbox = gtk_hbox_new(false, 2);
   button = gtk_button_new_with_label(_("Open"));
    gtk_signal_connect(GTK_OBJECT(button), "clicked", GTK_SIGNAL_FUNC(openFileButtonClicked), GINT_TO_POINTER(FS_MODE_INPUT));
   inputFilenameEntry = gtk_entry_new();
    gtk_box_pack_start_defaults(GTK_BOX(hbox), inputFilenameEntry);
    gtk_box_pack_start_defaults(GTK_BOX(hbox), button);
    gtk_table_attach_defaults(GTK_TABLE(table), hbox, 1, 2, 0, 1);
  //Init output file label and field
  label = gtk_label_new(_("Output file:"));
    gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 1, 2);
  hbox = gtk_hbox_new(false, 2);
   button = gtk_button_new_with_label(_("Open"));
     gtk_signal_connect(GTK_OBJECT(button), "clicked", GTK_SIGNAL_FUNC(openFileButtonClicked), GINT_TO_POINTER(FS_MODE_OUTPUT));
   outputFilenameEntry = gtk_entry_new();
    gtk_box_pack_start_defaults(GTK_BOX(hbox), outputFilenameEntry);
    gtk_box_pack_start_defaults(GTK_BOX(hbox), button);
    gtk_table_attach_defaults(GTK_TABLE(table), hbox, 1, 2, 1, 2);
  //Init password label and field
  label = gtk_label_new(_("Password:"));
    gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 2, 3);
  passwordEntry = gtk_entry_new();
    gtk_entry_set_visibility(GTK_ENTRY(passwordEntry), false);
    gtk_table_attach_defaults(GTK_TABLE(table), passwordEntry, 1, 2, 2, 3);
  //Init password verification label and field
  label = gtk_label_new(_("Password again:"));
    gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 3, 4);
  passwordVerifyEntry = gtk_entry_new();
    gtk_entry_set_visibility(GTK_ENTRY(passwordVerifyEntry), false);
    gtk_table_attach_defaults(GTK_TABLE(table), passwordVerifyEntry, 1, 2, 3, 4);
  //Init widgets to choose mode (encrypt or decrypt)
  label = gtk_label_new(_("Operation:"));
   gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 4, 5);
  hbox = gtk_hbox_new(false, 2);
  encryptRadioButton = gtk_radio_button_new_with_label(NULL, _("Encrypt"));
    gtk_toggle_button_set_active(GTK_TOGGLE_BUTTON(encryptRadioButton), true);
    gtk_box_pack_start_defaults(GTK_BOX(hbox), encryptRadioButton);
  decryptRadioButton = gtk_radio_button_new_with_label(gtk_radio_button_group(GTK_RADIO_BUTTON(encryptRadioButton)), _("Decrypt"));
    gtk_box_pack_start_defaults(GTK_BOX(hbox), decryptRadioButton);
   gtk_widget_show_all(hbox);
   gtk_table_attach_defaults(GTK_TABLE(table), hbox, 1, 2, 4, 5);
  //Init widgets to choose algorithm (AES/Twofish/Serpent)
  label = gtk_label_new(_("Algorithm:"));
   gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 5, 6);
  hbox = gtk_hbox_new(false, 3);
  aesRadioButton = gtk_radio_button_new_with_label(NULL, _("AES"));
    gtk_box_pack_start_defaults(GTK_BOX(hbox), aesRadioButton);
  twofishRadioButton = gtk_radio_button_new_with_label(gtk_radio_button_group(GTK_RADIO_BUTTON(aesRadioButton)), _("Twofish"));
    gtk_box_pack_start_defaults(GTK_BOX(hbox), twofishRadioButton);
    gtk_toggle_button_set_active(GTK_TOGGLE_BUTTON(twofishRadioButton), true);
  serpentRadioButton = gtk_radio_button_new_with_label(gtk_radio_button_group(GTK_RADIO_BUTTON(twofishRadioButton)), _("Serpent"));
    gtk_box_pack_start_defaults(GTK_BOX(hbox), serpentRadioButton);
   gtk_widget_show_all(hbox);
   gtk_table_attach_defaults(GTK_TABLE(table), hbox, 1, 2, 5, 6);
   //Init OK button
  okButton = gtk_button_new_with_label(_("OK"));
    gtk_table_attach_defaults(GTK_TABLE(table), okButton, 0, 2, 6, 7);
    gtk_signal_connect(GTK_OBJECT(okButton), "clicked", GTK_SIGNAL_FUNC(okButtonClicked), NULL);

  /* Enter the main loop */
  gtk_container_add (GTK_CONTAINER (win), table);
  gtk_widget_show_all(win);
  gtk_widget_show_all(hbox);
  gtk_main ();
  return 0;
}
