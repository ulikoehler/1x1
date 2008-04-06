/***************************************************************
 * Name:      GleichungstrainerMain.h
 * Purpose:   Defines Application Frame
 * Author:    Uli Köhler ()
 * Created:   2007-12-07
 * Copyright: Uli Köhler ()
 * License:
 **************************************************************/

#ifndef GLEICHUNGSTRAINERMAIN_H
#define GLEICHUNGSTRAINERMAIN_H

#include <math.h>
#include <string>
#include <sstream>

#include <iostream>

using namespace std;

//(*Headers(GleichungstrainerFrame)
#include <wx/stattext.h>
#include <wx/textctrl.h>
#include <wx/radiobut.h>
#include <wx/button.h>
#include <wx/frame.h>
//*)

class GleichungstrainerFrame: public wxFrame
{
    public:

        GleichungstrainerFrame(wxWindow* parent,wxWindowID id = -1);
        virtual ~GleichungstrainerFrame();

    private:

        //(*Handlers(GleichungstrainerFrame)
        void OnQuit(wxCommandEvent& event);
        void OnAbout(wxCommandEvent& event);
        void OnTextCtrl1Text(wxCommandEvent& event);
        void OnNewExerciseButtonClick(wxCommandEvent& event);
        void OnNextStepButtonClick(wxCommandEvent& event);
        void OnCheckSolutionButtonClick(wxCommandEvent& event);
        //*)
        ///User-defined variables
        int g;
        int zufallszahl;
        int counter;
        double x;
        double x1;
        void clearSS(); //Clear Stringstreams

        double k1;
        double k2;
        double k3;
        double k4;
        double k5;
        double k6;
        double k7;
        double k8;
        double k9;

        stringstream aufgabe;
        stringstream schritt1;
        stringstream schritt2;
        stringstream schritt3;
        stringstream schritt4;
        stringstream schritt5;
        stringstream schritt6;
        stringstream schritt7;
        stringstream schritt8;
        stringstream schritt9;
        ///User defined functions
        void newExercise();

        //(*Identifiers(GleichungstrainerFrame)
        static const long ID_STATICTEXT2;
        static const long ID_STATICTEXT3;
        static const long ID_STATICTEXT4;
        static const long ID_STATICTEXT1;
        static const long ID_TEXTCTRL1;
        static const long ID_RADIOBUTTON1;
        static const long ID_RADIOBUTTON2;
        static const long ID_TEXTCTRL3;
        static const long ID_TEXTCTRL2;
        static const long ID_TEXTCTRL4;
        static const long ID_BUTTON2;
        static const long ID_BUTTON3;
        static const long ID_BUTTON1;
        static const long ID_TEXTCTRL5;
        //*)

        //(*Declarations(GleichungstrainerFrame)
        wxButton* checkSolutionButton;
        wxButton* newExerciseButton;
        wxTextCtrl* exerciseField;
        wxTextCtrl* yourSolutionField;
        wxTextCtrl* correctSolutionField;
        wxTextCtrl* stepsField;
        wxRadioButton* difficultRadioButton;
        wxStaticText* yourSolutionLabel;
        wxButton* nextStepButton;
        wxStaticText* correctSolutionLabel;
        wxStaticText* difficultlyLabel;
        wxStaticText* exerciseLabel;
        wxTextCtrl* correctnessLabel;
        wxRadioButton* simpleRadioButton;
        //*)

        DECLARE_EVENT_TABLE()
};

#endif // GLEICHUNGSTRAINERMAIN_H
