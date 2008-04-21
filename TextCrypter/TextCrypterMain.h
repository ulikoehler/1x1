/***************************************************************
 * Name:      TextCrypterMain.h
 * Purpose:   Defines Application Frame
 * Author:    Uli Koehler ()
 * Created:   2008-04-21
 * Copyright: Uli Koehler ()
 * License:
 **************************************************************/

#ifndef TEXTCRYPTERMAIN_H
#define TEXTCRYPTERMAIN_H

//(*Headers(TextCrypterFrame)
#include <wx/stattext.h>
#include <wx/textctrl.h>
#include <wx/checkbox.h>
#include <wx/button.h>
#include <wx/frame.h>
//*)

class TextCrypterFrame: public wxFrame
{
    public:

        TextCrypterFrame(wxWindow* parent,wxWindowID id = -1);
        virtual ~TextCrypterFrame();

    private:

        //(*Handlers(TextCrypterFrame)
        void OnQuit(wxCommandEvent& event);
        void OnAbout(wxCommandEvent& event);
        void OnOkButtonClick(wxCommandEvent& event);
        //*)

        //(*Identifiers(TextCrypterFrame)
        static const long ID_STATICTEXT1;
        static const long ID_TEXTCTRL1;
        static const long ID_TEXTCTRL3;
        static const long ID_TEXTCTRL2;
        static const long ID_STATICTEXT3;
        static const long ID_STATICTEXT2;
        static const long ID_CHECKBOX1;
        static const long ID_BUTTON1;
        //*)

        //(*Declarations(TextCrypterFrame)
        wxTextCtrl* outputField;
        wxStaticText* outputLabel;
        wxTextCtrl* passwordField;
        wxStaticText* passwordLabel;
        wxStaticText* inputLabel;
        wxCheckBox* decryptCheckbox;
        wxButton* okButton;
        wxTextCtrl* inputField;
        //*)

        ///Own stuff
        void encrypt();
        void decrypt();

        DECLARE_EVENT_TABLE()
};

#endif // TEXTCRYPTERMAIN_H
