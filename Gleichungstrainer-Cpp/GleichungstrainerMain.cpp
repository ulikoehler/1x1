/***************************************************************
 * Name:      GleichungstrainerMain.cpp
 * Purpose:   Code for Application Frame
 * Author:    Uli Köhler ()
 * Created:   2007-12-07
 * Copyright: Uli Köhler ()
 * License:
 **************************************************************/

#include "wx_pch.h"
#include "GleichungstrainerMain.h"
#include <wx/msgdlg.h>
#define UNICODE

//(*InternalHeaders(GleichungstrainerFrame)
#include <wx/settings.h>
#include <wx/font.h>
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

//(*IdInit(GleichungstrainerFrame)
const long GleichungstrainerFrame::ID_STATICTEXT2 = wxNewId();
const long GleichungstrainerFrame::ID_STATICTEXT3 = wxNewId();
const long GleichungstrainerFrame::ID_STATICTEXT4 = wxNewId();
const long GleichungstrainerFrame::ID_STATICTEXT1 = wxNewId();
const long GleichungstrainerFrame::ID_TEXTCTRL1 = wxNewId();
const long GleichungstrainerFrame::ID_RADIOBUTTON1 = wxNewId();
const long GleichungstrainerFrame::ID_RADIOBUTTON2 = wxNewId();
const long GleichungstrainerFrame::ID_TEXTCTRL3 = wxNewId();
const long GleichungstrainerFrame::ID_TEXTCTRL2 = wxNewId();
const long GleichungstrainerFrame::ID_TEXTCTRL4 = wxNewId();
const long GleichungstrainerFrame::ID_BUTTON2 = wxNewId();
const long GleichungstrainerFrame::ID_BUTTON3 = wxNewId();
const long GleichungstrainerFrame::ID_BUTTON1 = wxNewId();
const long GleichungstrainerFrame::ID_TEXTCTRL5 = wxNewId();
//*)

BEGIN_EVENT_TABLE(GleichungstrainerFrame,wxFrame)
    //(*EventTable(GleichungstrainerFrame)
    //*)
END_EVENT_TABLE()

GleichungstrainerFrame::GleichungstrainerFrame(wxWindow* parent,wxWindowID id)
{
    //(*Initialize(GleichungstrainerFrame)
    Create(parent, wxID_ANY, _("Gleichungstrainer"), wxDefaultPosition, wxDefaultSize, wxDEFAULT_FRAME_STYLE, _T("wxID_ANY"));
    SetClientSize(wxSize(400,451));
    difficultlyLabel = new wxStaticText(this, ID_STATICTEXT2, _("Schwierigkeit:"), wxPoint(16,32), wxDefaultSize, 0, _T("ID_STATICTEXT2"));
    wxFont difficultlyLabelFont(12,wxSWISS,wxFONTSTYLE_NORMAL,wxNORMAL,false,_T("Arial"),wxFONTENCODING_DEFAULT);
    difficultlyLabel->SetFont(difficultlyLabelFont);
    exerciseLabel = new wxStaticText(this, ID_STATICTEXT3, _("Aufgabe:"), wxPoint(16,80), wxDefaultSize, 0, _T("ID_STATICTEXT3"));
    wxFont exerciseLabelFont(12,wxSWISS,wxFONTSTYLE_NORMAL,wxNORMAL,false,_T("Arial"),wxFONTENCODING_DEFAULT);
    exerciseLabel->SetFont(exerciseLabelFont);
    yourSolutionLabel = new wxStaticText(this, ID_STATICTEXT4, _("Deine Loesung:"), wxPoint(16,120), wxDefaultSize, 0, _T("ID_STATICTEXT4"));
    wxFont yourSolutionLabelFont(12,wxSWISS,wxFONTSTYLE_NORMAL,wxNORMAL,false,_T("Arial"),wxFONTENCODING_DEFAULT);
    yourSolutionLabel->SetFont(yourSolutionLabelFont);
    correctSolutionLabel = new wxStaticText(this, ID_STATICTEXT1, _("Korrekte Loesung (x):"), wxPoint(16,152), wxDefaultSize, 0, _T("ID_STATICTEXT1"));
    wxFont correctSolutionLabelFont(12,wxSWISS,wxFONTSTYLE_NORMAL,wxNORMAL,false,_T("Arial"),wxFONTENCODING_DEFAULT);
    correctSolutionLabel->SetFont(correctSolutionLabelFont);
    exerciseField = new wxTextCtrl(this, ID_TEXTCTRL1, wxEmptyString, wxPoint(88,80), wxSize(288,21), wxTE_READONLY, wxDefaultValidator, _T("ID_TEXTCTRL1"));
    exerciseField->SetBackgroundColour(wxSystemSettings::GetColour(wxSYS_COLOUR_GRAYTEXT));
    simpleRadioButton = new wxRadioButton(this, ID_RADIOBUTTON1, _("Einfach"), wxPoint(120,32), wxDefaultSize, wxRB_GROUP, wxDefaultValidator, _T("ID_RADIOBUTTON1"));
    simpleRadioButton->SetValue(true);
    difficultRadioButton = new wxRadioButton(this, ID_RADIOBUTTON2, _("Schwierig"), wxPoint(184,32), wxDefaultSize, 0, wxDefaultValidator, _T("ID_RADIOBUTTON2"));
    yourSolutionField = new wxTextCtrl(this, ID_TEXTCTRL3, wxEmptyString, wxPoint(136,120), wxDefaultSize, 0, wxDefaultValidator, _T("ID_TEXTCTRL3"));
    correctSolutionField = new wxTextCtrl(this, ID_TEXTCTRL2, wxEmptyString, wxPoint(176,152), wxDefaultSize, wxTE_READONLY, wxDefaultValidator, _T("ID_TEXTCTRL2"));
    correctSolutionField->SetBackgroundColour(wxSystemSettings::GetColour(wxSYS_COLOUR_GRAYTEXT));
    stepsField = new wxTextCtrl(this, ID_TEXTCTRL4, wxEmptyString, wxPoint(16,184), wxSize(368,176), wxTE_MULTILINE|wxTE_READONLY, wxDefaultValidator, _T("ID_TEXTCTRL4"));
    stepsField->SetBackgroundColour(wxSystemSettings::GetColour(wxSYS_COLOUR_GRAYTEXT));
    checkSolutionButton = new wxButton(this, ID_BUTTON2, _("Loesung pruefen"), wxPoint(16,416), wxSize(88,23), 0, wxDefaultValidator, _T("ID_BUTTON2"));
    nextStepButton = new wxButton(this, ID_BUTTON3, _("Naechsten Schritt anzeigen"), wxPoint(112,416), wxSize(152,23), 0, wxDefaultValidator, _T("ID_BUTTON3"));
    newExerciseButton = new wxButton(this, ID_BUTTON1, _("Neue Aufgabe"), wxPoint(272,416), wxSize(112,23), 0, wxDefaultValidator, _T("ID_BUTTON1"));
    correctnessLabel = new wxTextCtrl(this, ID_TEXTCTRL5, wxEmptyString, wxPoint(24,368), wxSize(352,40), wxTE_READONLY|wxTE_CENTRE|wxNO_BORDER, wxDefaultValidator, _T("ID_TEXTCTRL5"));
    correctnessLabel->SetBackgroundColour(wxSystemSettings::GetColour(wxSYS_COLOUR_APPWORKSPACE));
    wxFont correctnessLabelFont(24,wxSWISS,wxFONTSTYLE_NORMAL,wxNORMAL,false,wxEmptyString,wxFONTENCODING_DEFAULT);
    correctnessLabel->SetFont(correctnessLabelFont);

    Connect(ID_TEXTCTRL1,wxEVT_COMMAND_TEXT_UPDATED,(wxObjectEventFunction)&GleichungstrainerFrame::OnTextCtrl1Text);
    Connect(ID_BUTTON2,wxEVT_COMMAND_BUTTON_CLICKED,(wxObjectEventFunction)&GleichungstrainerFrame::OnCheckSolutionButtonClick);
    Connect(ID_BUTTON3,wxEVT_COMMAND_BUTTON_CLICKED,(wxObjectEventFunction)&GleichungstrainerFrame::OnNextStepButtonClick);
    Connect(ID_BUTTON1,wxEVT_COMMAND_BUTTON_CLICKED,(wxObjectEventFunction)&GleichungstrainerFrame::OnNewExerciseButtonClick);
    //*)
    srand(time(0));
    newExercise();
}

GleichungstrainerFrame::~GleichungstrainerFrame()
{
    //(*Destroy(GleichungstrainerFrame)
    //*)
}

void GleichungstrainerFrame::OnQuit(wxCommandEvent& event)
{
    Close();
}

void GleichungstrainerFrame::OnAbout(wxCommandEvent& event)
{
    wxString msg = wxbuildinfo(long_f);
    wxMessageBox(msg, _("Welcome to..."));
}

void GleichungstrainerFrame::OnTextCtrl1Text(wxCommandEvent& event)
{
}

void GleichungstrainerFrame::newExercise()
{
    clearSS(); ///Clear Stringstreams
    //Init random number
    zufallszahl = rand()%10+1;
    //Set g
    if(simpleRadioButton->GetValue()) {g = 8;}
    else {g = 95;}
    //Init z vars
     int z0=rand()%g+1;
     int z1=rand()%g+2;
     int z2=rand()%g+2;
     int z3=rand()%g+2;
     int z4=rand()%g+2;
     int z5=rand()%g+2;
     //double z6=rand()%g+2;
     int z7=rand()%(g/3)+2;
     //double z8=7*rand()%g+2;
     //double z9=floor(7*rand())+2;

    ///Main Switch
    switch(zufallszahl)
    {
     case 1:
            {
                x=z0;
                k1=z1+z7;
                k6=k1*x;
                k5=z3;
                k2=k6+k5;
                k4=floor(z4/5)+2;
                k3=k4*k5;

                aufgabe << "   " << k2 << " - " << k1 << " * x  =  " << k3 << " : " << k4;
                x1=x;
                schritt1 << "  " << k2 << " - " << k1 << " * x  =  " << k5;
                schritt2 << "  " << k1 << " * x  =  " << k2 << " - " << k5;
                schritt3 << "  " << k1 << " * x  =  " << (k2-k5);
                schritt4 << "  x  =  " << (k2-k5) << " : " << k1;
                schritt5 << "  x  =  " << x;
                schritt6 << "  fertig!";
                schritt7 << "";
                schritt8 << "";
                schritt9 << "";
                break;
            }
    case 2:
            {
                k1=z2;
                k2=z2+z1;
                k3=z3;
                k4=z7+3;
                x=(k2+k3)*k4-k1;
                k5=k2+k3;
                x1=x;

                aufgabe << "  ( x + " << k1 << " ) : " << k4 << "  =  " << k2 << " + " << k3;
                schritt1 <<  "  ( x + " << k1 << " ) : " << k4 << "  =  " << (k2+k3);
                schritt2 <<  "  ( x + " << k1 << " )  =  " << k5 << " * " << k4;
                schritt3 << "  x + " << k1 << "  =  " << (k5 * k4);
                schritt4 <<  "  x  =  " << (k5 * k4) << " - " << k1;
                schritt5 <<  "  x  =  " << x1;
                schritt6 <<  "  fertig!";
                 schritt7 << "";
                 schritt8 <<  "";
                 schritt9 <<  "";
                break;
            }
    case 3:
            {
                x=z2;
                k2=x;
                k1=z1;
                k3=z7;
                k4=z4;
                k5=(k1*k2+k3)*k4;
                k6=floor((k5-k4)/2)+2;
                k7=k5-k6;

                aufgabe << "   ( x * " << k1 << " + " << k3 << " ) * "<< k4 << "  =  " << k6 << " + " << k7;
                x1=x;
                schritt1 << "  ( x * " << k1 << " + " << k3 << " ) * " << k4 << "  =  " << k5;
                schritt2 << "  ( x * " << k1 << " + " << k3 << " )  =  " << k5 << " : " << k4;
                schritt3 << "  x * " << k1 << " + " << k3 << "  =  " << (k1 * k2 + k3);
                schritt4 << "  x * " << k1 << "  =  " << (k1 * k2 + k3) << " - " << k3;
                schritt5 << "  x * " << k1 << "  =  " << (k1 * k2);
                schritt6 << "  x  =  " << (k1 *  k2) << " : " << k1;
                schritt7 << "  x  =  " << k2;
                schritt8 << "  fertig! ";
                schritt9 <<  "";
                break;
            }
    case 4:
            {
                if((z2*z3)<(z4*z5))
                {
                    x=z2;
                    k1=z3;
                    k2=z4;
                    k3=z5;
                    k4=z4*z5-z2*z3;
                }
                else
                {
                        x=z4;
                        k1=z5;
                        k2=z2+1;
                        k3=z3+1;
                        k4=k3*k2-k1*x;
                }
                aufgabe << "   " << k1 <<" * x + " << k4 << "  =  " << k2 << " * " << k3;
                x1=x;
                schritt1 << "  " << k1 << " * x + " << k4 << "  =  " << (k2*k3);
                schritt2 << "  " << k1 << " * x  =  " << (k2 * k3) << " - " << k4;
                schritt3 << "  " << k1 << " * x  =  " << ((k2*k3)-k4);
                schritt4 << "  x  =  " << ((k2*k3)-k4) << " : " << k1;
                schritt5 << "  x  =  " << x1;
                schritt6 << "  fertig!";
                schritt7 << "";
                schritt8 <<  "";
                schritt9 << "";
            break;
            }
    case 5:
            {
                k2=z2;
                k3=z3;
                k4=z4;
                if (k2>k4)
                {
                    k2=z4; k4=z2;
                }
                k5=k3*k4-k2;
                k6=z1+3;
                x=k6*k2;

                aufgabe << "  " << k5 << " + x : " << k6 << "  =  " << k3 << " * " << k4;
                x1=x;
                schritt1 << "  " << k5 << " + x : " << k6 << "  =  " << (k3*k4);
                schritt2 << "  x : " << k6 << "  =  " << (k3*k4) << " - " << k5;
                schritt3 << "  x : " << k6 << "  =  " << ((k3*k4)-k5);
                schritt4 << "  x  =  " << ((k3*k4)-k5) << " * " << k6;
                schritt5 << "  x  =  " << x;
                schritt6 << "  fertig!";
                schritt7 << "";
                schritt8 << "";
                schritt9 << "";
                break;
            }
    case 6:
            {
                k1=z1;
                k2=z2;
                k3=k1*k2;
                k4=z7;
                k5=k4+k2;
                k6=z3;
                x=k6;
                k7=k6*k4;

                aufgabe << "   " << k5 << " - " << k7 << " : x  =  " << k3 << " : " << k1;
                x1=x;
                schritt1 <<  "  " << k5 << " - " << k7 << " : x  =  " << k2;
                schritt2 << "  " << k7 << " : x  =  " << k5 << " - " << k2;
                schritt3 << "  " << k7 << " : x  =  " << k4;
                schritt4 << "  x  =  " << k7 << " : " << k4;
                schritt5 << "  x  =  " << k6;
                schritt6 << "  fertig!";
                schritt7 << "";
                schritt8 << "";
                schritt9 << "";
                break;
            }
    case 7:
            {
                k1=z3;
                k2=z2;
                k6=k1*k2;
                k3=z4;
                k4=z5;
                if ((k3*k4)<(k1+1)) {k3=k1+2;}
                k5=k3*k4-k1;
                x=k4;

                aufgabe << "   " << k3 << " * x  -  " << k5 << "  =  " << k6 << " : " << k2;
                x1=x;
                schritt1 << "  " << k3 << " * x  -  " << k5 << "  =  " << k1;
                schritt2 << "  " << k3 << " * x  =  " << k1 << " + " << k5;
                schritt3 << "  " << k3 << " * x  =  " << (k1+k5);
                schritt4 << "  x  =  " << (k1+k5) << " : " << k3;
                schritt5 << "  x  =  " << k4;
                schritt6 << "  fertig!";
                schritt7 << "";
                schritt8 << "";
                schritt9 << "";
            break;
            }
    case 8:
            {
                k1=z1;
                k2=z2;
                k3=z3;
                k4=z4;
                k5=z5;
                k6=k4+k5;
                k7=k4*(k3+k1*k2);
                x=k2;
                aufgabe << "   " << k7 << " : ( " << k3 << " + " << k1 << " * x )  =  " << k6 << " - " << k5;
                x1=x;
                schritt1 << "  " << k7 << " : ( " << k3 << " + " << k1 << " * x )  =  " << k4;
                schritt2 << "  ( " << k3 << " + " << k1 << " * x )  =  " << k7 << " : " << k4;
                schritt3 << "  " << k3 << " + " << k1 << " * x  =  " << (k7/k4);
                schritt4 << "  " << k1 << " * x  =  " << (k7/k4) << " - " << k3;
                schritt5 << "  " << k1 << " * x  =  " << (k7/k4-k3);
                schritt6 << "  x  =  " << (k7/k4-k3) << " : " << k1;
                schritt7 << "  x  =  " << k2;
                schritt8 << "  fertig!";
                schritt9 << "";
                break;
            }
    case 9:
            {
                k2=z2;
                k3=z3;
                k4=k2+k3;
                k5=z4;
                x=k2*k5;

                aufgabe << "   x : " << k5 << " + " << k3 << "  =  " << k4;
                x1=x;
                schritt1 << "  x : " << k5 << "  =  " << k4 << " - " << k3;
                schritt2 << "  x : " << k5 << "  =  " << k2;
                schritt3 << "  x  =  " << k2 << " * " << k5;
                schritt4 << "  x  =  " << x;
                schritt5 << "  Diese Aufgabe von Pirmin ist fertig!";
                schritt6 << "";
                schritt7 << "";
                schritt8 << "";
                schritt9 << "";
                break;
            }
    case 10:
            {
                k2=z2;
                k3=z3;
                k4=k2+k3;
                x=z4;
                k5=z4*k4;

                aufgabe << "   " << k5 << " : x - " << k2 << "  =  " << k3;
                x1=x;
                schritt1 << "  " << k5 << " : x  =  " << k3 << " + " << k2;
                schritt2 << "  " << k5 << " : x  =  "<< k4;
                schritt3 << "  x  =  " << k5<< " : "<< k4;
                schritt4 << "  x  =  " << x;
                schritt5 << "  fertig!";
                schritt6 << "";
                schritt7 << "";
                schritt8 << "";
                schritt9 << "";
                break;
            }
    default: break;
    }
    exerciseField->SetValue(wxString(aufgabe.str().c_str(), wxConvUTF8));
    aufgabe.str(""); ///Clear stringstream
}

void GleichungstrainerFrame::OnNewExerciseButtonClick(wxCommandEvent& event)
{
    newExercise();
}

void GleichungstrainerFrame::clearSS()
{
    counter = 1;
    schritt1.str("");
    schritt2.str("");
    schritt3.str("");
    schritt4.str("");
    schritt5.str("");
    schritt6.str("");
    schritt7.str("");
    schritt8.str("");
    schritt9.str("");
}

void GleichungstrainerFrame::OnNextStepButtonClick(wxCommandEvent& event)
{
    switch(counter)
    {
        case 1: stepsField->AppendText(wxString(schritt1.str().c_str(), wxConvUTF8));break;
        case 2: stepsField->AppendText(wxString(schritt2.str().c_str(), wxConvUTF8));break;
        case 3: stepsField->AppendText(wxString(schritt3.str().c_str(), wxConvUTF8));break;
        case 4: stepsField->AppendText(wxString(schritt4.str().c_str(), wxConvUTF8));break;
        case 5: stepsField->AppendText(wxString(schritt5.str().c_str(), wxConvUTF8));break;
        case 6: stepsField->AppendText(wxString(schritt6.str().c_str(), wxConvUTF8));break;
        case 7: stepsField->AppendText(wxString(schritt7.str().c_str(), wxConvUTF8));break;
        case 8: stepsField->AppendText(wxString(schritt8.str().c_str(), wxConvUTF8));break;
        case 9: stepsField->AppendText(wxString(schritt9.str().c_str(), wxConvUTF8));break;
        default: break;
    }
    stepsField->AppendText(wxString("\n", wxConvUTF8));
    counter++;
}

void GleichungstrainerFrame::OnCheckSolutionButtonClick(wxCommandEvent& event)
{
    stringstream xstream;
    xstream << x;
    correctSolutionField->SetValue(wxString(xstream.str().c_str(), wxConvUTF8));
    string ys = (const char*) yourSolutionField->GetValue().mb_str(wxConvUTF8); ///YourSolution
    if(ys.c_str() == xstream.str())
    {
        correctnessLabel->SetForegroundColour(wxColour(0, 255, 0));
        correctnessLabel->SetValue(wxString("Richtig!", wxConvUTF8));
    }
    else
    {
        correctnessLabel->SetForegroundColour(wxColour(255, 0, 0));
        correctnessLabel->SetValue(wxString("Falsch!", wxConvUTF8));
    }
}
