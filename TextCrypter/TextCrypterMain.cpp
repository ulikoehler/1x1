/***************************************************************
 * Name:      TextCrypterMain.cpp
 * Purpose:   Code for Application Frame
 * Author:    Uli Koehler ()
 * Created:   2008-04-21
 * Copyright: Uli Koehler ()
 * License:
 **************************************************************/

#include "wx_pch.h"
#include "TextCrypterMain.h"
#include <wx/msgdlg.h>
#include <tomcrypt.h>
#include <string>
#include <boost/lexical_cast.hpp>

using namespace std;
using namespace boost;

//(*InternalHeaders(TextCrypterFrame)
#include <wx/intl.h>
#include <wx/string.h>
//*)
void encrypt();
void decrypt();

//helper functions
enum wxbuildinfoformat {
    short_f, long_f };

wxString wxbuildinfo(wxbuildinfoformat format)
{
    wxString wxbuild(wxVERSION_STRING);

    if (format == long_f )
    {
#if defined(__WXMSW__)
        wxbuild << _T("-Windows");
#elif defined(__UNIX__)
        wxbuild << _T("-Linux");
#endif

#if wxUSE_UNICODE
        wxbuild << _T("-Unicode build");
#else
        wxbuild << _T("-ANSI build");
#endif // wxUSE_UNICODE
    }

    return wxbuild;
}

//(*IdInit(TextCrypterFrame)
const long TextCrypterFrame::ID_STATICTEXT1 = wxNewId();
const long TextCrypterFrame::ID_TEXTCTRL1 = wxNewId();
const long TextCrypterFrame::ID_TEXTCTRL3 = wxNewId();
const long TextCrypterFrame::ID_TEXTCTRL2 = wxNewId();
const long TextCrypterFrame::ID_STATICTEXT3 = wxNewId();
const long TextCrypterFrame::ID_STATICTEXT2 = wxNewId();
const long TextCrypterFrame::ID_CHECKBOX1 = wxNewId();
const long TextCrypterFrame::ID_BUTTON1 = wxNewId();
const long TextCrypterFrame::idMenuQuit = wxNewId();
const long TextCrypterFrame::idMenuAbout = wxNewId();
const long TextCrypterFrame::ID_STATUSBAR1 = wxNewId();
//*)

BEGIN_EVENT_TABLE(TextCrypterFrame,wxFrame)
    //(*EventTable(TextCrypterFrame)
    //*)
END_EVENT_TABLE()

TextCrypterFrame::TextCrypterFrame(wxWindow* parent,wxWindowID id)
{
    //(*Initialize(TextCrypterFrame)
    wxMenuItem* MenuItem2;
    wxMenuItem* MenuItem1;
    wxMenu* Menu1;
    wxMenuBar* MenuBar1;
    wxMenu* Menu2;

    Create(parent, wxID_ANY, _("TextCrypter"), wxDefaultPosition, wxDefaultSize, wxDEFAULT_FRAME_STYLE, _T("wxID_ANY"));
    SetClientSize(wxSize(433,450));
    inputLabel = new wxStaticText(this, ID_STATICTEXT1, _("Eingabe:"), wxPoint(16,16), wxDefaultSize, 0, _T("ID_STATICTEXT1"));
    inputField = new wxTextCtrl(this, ID_TEXTCTRL1, _("Text"), wxPoint(72,8), wxSize(352,176), wxTE_MULTILINE, wxDefaultValidator, _T("ID_TEXTCTRL1"));
    TextCtrl1 = new wxTextCtrl(this, ID_TEXTCTRL3, _("Text"), wxPoint(72,256), wxSize(352,176), wxTE_MULTILINE, wxDefaultValidator, _T("ID_TEXTCTRL3"));
    passwordField = new wxTextCtrl(this, ID_TEXTCTRL2, _("passwort"), wxPoint(72,192), wxSize(216,21), wxTE_PASSWORD, wxDefaultValidator, _T("ID_TEXTCTRL2"));
    outputLabel = new wxStaticText(this, ID_STATICTEXT3, _("Ausgabe:"), wxPoint(16,264), wxDefaultSize, 0, _T("ID_STATICTEXT3"));
    passwordLabel = new wxStaticText(this, ID_STATICTEXT2, _("Passwort:"), wxPoint(16,192), wxDefaultSize, 0, _T("ID_STATICTEXT2"));
    decryptCheckbox = new wxCheckBox(this, ID_CHECKBOX1, _("Entschlüsseln"), wxPoint(300,195), wxDefaultSize, 0, wxDefaultValidator, _T("ID_CHECKBOX1"));
    decryptCheckbox->SetValue(false);
    okButton = new wxButton(this, ID_BUTTON1, _("OK"), wxPoint(72,224), wxSize(352,23), 0, wxDefaultValidator, _T("ID_BUTTON1"));
    MenuBar1 = new wxMenuBar();
    Menu1 = new wxMenu();
    MenuItem1 = new wxMenuItem(Menu1, idMenuQuit, _("Quit\tAlt-F4"), _("Quit the application"), wxITEM_NORMAL);
    Menu1->Append(MenuItem1);
    MenuBar1->Append(Menu1, _("&File"));
    Menu2 = new wxMenu();
    MenuItem2 = new wxMenuItem(Menu2, idMenuAbout, _("About\tF1"), _("Show info about this application"), wxITEM_NORMAL);
    Menu2->Append(MenuItem2);
    MenuBar1->Append(Menu2, _("Help"));
    SetMenuBar(MenuBar1);
    StatusBar1 = new wxStatusBar(this, ID_STATUSBAR1, 0, _T("ID_STATUSBAR1"));
    int __wxStatusBarWidths_1[1] = { -1 };
    int __wxStatusBarStyles_1[1] = { wxSB_NORMAL };
    StatusBar1->SetFieldsCount(1,__wxStatusBarWidths_1);
    StatusBar1->SetStatusStyles(1,__wxStatusBarStyles_1);
    SetStatusBar(StatusBar1);

    Connect(ID_BUTTON1,wxEVT_COMMAND_BUTTON_CLICKED,(wxObjectEventFunction)&TextCrypterFrame::OnOkButtonClick);
    Connect(idMenuQuit,wxEVT_COMMAND_MENU_SELECTED,(wxObjectEventFunction)&TextCrypterFrame::OnQuit);
    Connect(idMenuAbout,wxEVT_COMMAND_MENU_SELECTED,(wxObjectEventFunction)&TextCrypterFrame::OnAbout);
    //*)
}

TextCrypterFrame::~TextCrypterFrame()
{
    //(*Destroy(TextCrypterFrame)
    //*)
}

void TextCrypterFrame::OnQuit(wxCommandEvent& event)
{
    Close();
}

void TextCrypterFrame::OnAbout(wxCommandEvent& event)
{
    wxString msg = wxbuildinfo(long_f);
    wxMessageBox(msg, _("Welcome to..."));
}

void TextCrypterFrame::OnOkButtonClick(wxCommandEvent& event)
{
    if(decryptCheckbox->IsChecked()) {decrypt();}
    else {encrypt();}
}

void TextCrypterFrame::encrypt()
{
    ///Get password uchar array
    unsigned char* password;
    string passwordString = lexical_cast<string>(passwordField->GetValue().c_str());
    hash_state md;
    sha256_init(&md);
    sha256_process(&md, (const unsigned char*)passwordString.c_str(), passwordString.length());
    sha256_done(&md, password);
    ///Setup serpent algorithm
    symmetric_key *skey;
    twofish_setup(password, 256, 32, skey);
    ///Get text and encrypt
    unsigned char* ciphertext;
    string plaintext = lexical_cast<string>(inputField->GetValue().c_str());
    twofish_ecb_encrypt((const unsigned char*)plaintext.c_str(), ciphertext, skey);
    ///Base64-encode the ciphertext
    unsigned char* encodedCiphertext;
    unsigned long* encodedLength;
    base64_encode(ciphertext, plaintext.length, encodedCiphertext, encodedLength);
    string encCTString(encodedCiphertext, *encodedLength);
    ///"Print" encoded ciphertext to GUI
    outputField->setText(new wxString(encCTString.c_str()));
}


void TextCrypterFrame::decrypt()
{

}

