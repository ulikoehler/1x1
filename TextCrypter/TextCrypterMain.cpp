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
#include <string>
#include <boost/lexical_cast.hpp>
#include "CryptoWrappers.h"

using namespace std;
using namespace boost;

//(*InternalHeaders(TextCrypterFrame)
#include <wx/intl.h>
#include <wx/string.h>
//*)

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
//*)

BEGIN_EVENT_TABLE(TextCrypterFrame,wxFrame)
    //(*EventTable(TextCrypterFrame)
    //*)
END_EVENT_TABLE()

TextCrypterFrame::TextCrypterFrame(wxWindow* parent,wxWindowID id)
{
    //(*Initialize(TextCrypterFrame)
    Create(parent, wxID_ANY, _("TextCrypter"), wxDefaultPosition, wxDefaultSize, wxDEFAULT_FRAME_STYLE, _T("wxID_ANY"));
    SetClientSize(wxSize(433,450));
    inputLabel = new wxStaticText(this, ID_STATICTEXT1, _("Eingabe:"), wxPoint(16,16), wxDefaultSize, 0, _T("ID_STATICTEXT1"));
    inputField = new wxTextCtrl(this, ID_TEXTCTRL1, _("Text"), wxPoint(72,8), wxSize(352,176), wxTE_MULTILINE, wxDefaultValidator, _T("ID_TEXTCTRL1"));
    outputField = new wxTextCtrl(this, ID_TEXTCTRL3, _("Text"), wxPoint(72,256), wxSize(352,176), wxTE_MULTILINE, wxDefaultValidator, _T("ID_TEXTCTRL3"));
    passwordField = new wxTextCtrl(this, ID_TEXTCTRL2, _("passwort"), wxPoint(72,192), wxSize(216,21), wxTE_PASSWORD, wxDefaultValidator, _T("ID_TEXTCTRL2"));
    outputLabel = new wxStaticText(this, ID_STATICTEXT3, _("Ausgabe:"), wxPoint(16,264), wxDefaultSize, 0, _T("ID_STATICTEXT3"));
    passwordLabel = new wxStaticText(this, ID_STATICTEXT2, _("Passwort:"), wxPoint(16,192), wxDefaultSize, 0, _T("ID_STATICTEXT2"));
    decryptCheckbox = new wxCheckBox(this, ID_CHECKBOX1, _("Entschlüsseln"), wxPoint(300,195), wxDefaultSize, 0, wxDefaultValidator, _T("ID_CHECKBOX1"));
    decryptCheckbox->SetValue(false);
    okButton = new wxButton(this, ID_BUTTON1, _("OK"), wxPoint(72,224), wxSize(352,23), 0, wxDefaultValidator, _T("ID_BUTTON1"));

    Connect(ID_BUTTON1,wxEVT_COMMAND_BUTTON_CLICKED,(wxObjectEventFunction)&TextCrypterFrame::OnOkButtonClick);
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
    if(decryptCheckbox->IsChecked()) {}//decrypt();}
    else
        {
            string plaintext = lexical_cast<string>(inputField->GetValue().c_str());
            string password = lexical_cast<string>(passwordField->GetValue().c_str());
            string ciphertext = botanEncrypt(plaintext, password, "Blowfish/CBC/PKCS7");
            outputField->SetValue(wxString(ciphertext.c_str(), wxConvUTF8));
        }
}



