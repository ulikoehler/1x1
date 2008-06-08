#include <stdlib.h>
#include <gtk/gtk.h>
#include <libintl.h>
#include <tomcrypt.h>

///Macros and Defines
#define _(x) gettext(x)
#define ROW_SPACING 3
#define COL_SPACING 3
#define TAB_COLS 0
#define TAB_ROWS 0

///Global variables
GtkWidget *win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
GtkWidget *table = gtk_table_new(TAB_ROWS, TAB_COLS, true);
GtkWidget *plaintextLabel;
GtkWidget *ciphertextLabel;
GtkWidget *passwordLabel;
GtkWidget *plaintextField;
GtkWidget *ciphertextField;
GtkWidget *passwordEntry;
GtkWidget *okButton;
GtkWidget *deencryptHbox; // deencryptHbox= de-encryptHbox
GtkWidget *decryptRadioButton;
GtkWidget *encryptRadioButton;


static void crypt(GtkWidget *wid, gpointer data)
{
    ///Hash password to get appropriate key length
    unsigned char hashedPw[32];
    char* pw;
    hash_state *md;
    sha256_init(md);
    pw = const_cast<char*>(gtk_entry_get_text(GTK_ENTRY(passwordEntry)));
    sha256_process(md, pw, strlen(pw));
    sha256_done(md, hashedPw);
    ///Setup cipher
    symmetric_key *twofishKey;
    unsigned char* input = gtk_entry_get_text(GTK_ENTRY(plaintextEntry));
    unsigned char* output = (unsigned char*) malloc(strlen(input)+1);
    twofish_setup(hashedPw, 32, 16, twofishKey);
    if(gtk_toggle_button_get_active(GTK_TOGGLE_BUTTON(encryptRadioButton)))
    {
        twofish_ecb_encrypt(input, output, twofishKey);
    }
    else //Decrypt
    {
        twofish_ecb_decrypt(input, output, twofishKey);
    }
    gtk_entry_set_text(GTK_ENTRY(ciphertextEntry), output);
    free(output);

int main (int argc, char *argv[])
{
  gtk_init (&argc, &argv);

  //Set some parameters
  gtk_window_set_title (GTK_WINDOW (win), _("GtkCrypter"));
  gtk_window_set_position (GTK_WINDOW (win), GTK_WIN_POS_CENTER);
  gtk_widget_realize (win);
  gtk_table_set_row_spacings(GTK_TABLE(table), ROW_SPACING);
  gtk_table_set_col_spacings(GTK_TABLE(table), COL_SPACING);
  g_signal_connect (win, "destroy", gtk_main_quit, NULL);

  ///Init main widgets
  plaintextLabel = gtk_label_new(_("Plaintext:"));
    gtk_table_attach_defaults(GTK_TABLE(table), plaintextLabel, 0, 1, 0, 1);
  plaintextField =gtk_text_view_new();
    gtk_table_attach_defaults(GTK_TABLE(table), plaintextField, 1, 2, 0, 1);
  ciphertextLabel = gtk_entry_new(_("Ciphertext:"));
    gtk_table_attach_defaults(GTK_TABLE(table), ciphertextLabel, 0, 1, 1, 2);
  ciphertextField = gtk_entry_new();
    gtk_table_attach_defaults(GTK_TABLE(table), ciphertextField, 1, 2, 1, 2);
  passwordLabel = gtk_label_new(_("Password"));
    gtk_table_attach_defaults(GTK_TABLE(table), passwordLabel, 0, 1, 2, 3);
  passwordEntry = gtk_entry_new();
    gtk_entry_set_visibility(GTK_ENTRY(passwordEntry), false);
    gtk_table_attach_defaults(GTK_TABLE(table), passwordEntry, 1, 2, 2, 3);
  okButton = gtk_button_new_with_label(_("OK"));
    gtk_table_attach_defaults(GTK_TABLE(table), okButton, 0, 1, 3, 4);
    gtk_signal_connect(GTK_OBJECT(okButton), "clicked", GTK_SIGNAL_FUNC(crypt), NULL);
  deencryptHbox = gtk_hbox_new(false, 3);
  encryptRadioButton = gtk_radio_button_new_with_label(NULL, _("Encrypt"));
    gtk_toggle_button_set_active(GTK_TOGGLE_BUTTON(encryptRadioButton), true);
    gtk_box_pack_start_defaults(GTK_BOX(deencryptHbox), encryptRadioButton);
  decryptRadioButton = gtk_radio_button_new_with_label(gtk_radio_button_group(GTK_RADIO_BUTTON(encryptRadioButton)), _("Decrypt"));
    gtk_box_pack_start_defaults(GTK_BOX(deencryptHbox), decryptRadioButton);
   gtk_widget_show_all(deencryptHbox);
   gtk_table_attach_defaults(GTK_TABLE(table), deencryptHbox, 1, 2, 3, 4);

  /* Enter the main loop */
  gtk_container_add (GTK_CONTAINER (win), table);
  gtk_widget_show_all(win);
  gtk_widget_show_all(deencryptHbox);
  gtk_main ();
  return 0;
}
