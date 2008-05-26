/***************************************************************
 * Name:      MoneyCounterMain.cpp
 * Purpose:   Code for Application Frame
 * Author:    Uli Koehler ()
 * Created:   2008-05-11
 * Copyright: Uli Koehler ()
 * License:
 **************************************************************/

#include "wx_pch.h"
#include "MoneyCounterMain.h"
#include <wx/msgdlg.h>

//(*InternalHeaders(MoneyCounterFrame)
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

//(*IdInit(MoneyCounterFrame)
const long MoneyCounterFrame::ID_SPINCTRL16 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX16 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL15 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL14 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX14 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL13 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL12 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL11 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL10 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX13 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX12 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX11 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX10 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL1 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX8 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX9 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX1 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX7 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX2 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX6 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX5 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX4 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX3 = wxNewId();
const long MoneyCounterFrame::ID_STATICTEXT1 = wxNewId();
const long MoneyCounterFrame::ID_TEXTCTRL1 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL9 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL8 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL7 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL6 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL5 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL4 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL3 = wxNewId();
const long MoneyCounterFrame::ID_SPINCTRL2 = wxNewId();
const long MoneyCounterFrame::ID_STATICBOX15 = wxNewId();
const long MoneyCounterFrame::idMenuQuit = wxNewId();
const long MoneyCounterFrame::ID_MENUITEM1 = wxNewId();
const long MoneyCounterFrame::ID_STATUSBAR1 = wxNewId();
//*)

BEGIN_EVENT_TABLE(MoneyCounterFrame,wxFrame)
    //(*EventTable(MoneyCounterFrame)
    //*)
END_EVENT_TABLE()

MoneyCounterFrame::MoneyCounterFrame(wxWindow* parent,wxWindowID id)
{
    //(*Initialize(MoneyCounterFrame)
    wxMenuItem* MenuItem2;
    wxMenuItem* MenuItem1;
    wxMenu* Menu1;
    wxMenuBar* MenuBar1;
    wxMenu* Menu2;

    Create(parent, wxID_ANY, _("MoneyCounter"), wxDefaultPosition, wxDefaultSize, wxDEFAULT_FRAME_STYLE, _T("wxID_ANY"));
    SetClientSize(wxSize(530,432));
    SpinCtrl16 = new wxSpinCtrl(this, ID_SPINCTRL16, _T("0"), wxPoint(16,320), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL16"));
    SpinCtrl16->SetValue(_T("0"));
    StaticBox16 = new wxStaticBox(this, ID_STATICBOX16, _("500 Euros:"), wxPoint(8,296), wxSize(96,88), 0, _T("ID_STATICBOX16"));
    SpinCtrl15 = new wxSpinCtrl(this, ID_SPINCTRL15, _T("0"), wxPoint(432,224), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL15"));
    SpinCtrl15->SetValue(_T("0"));
    SpinCtrl14 = new wxSpinCtrl(this, ID_SPINCTRL14, _T("0"), wxPoint(328,224), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL14"));
    SpinCtrl14->SetValue(_T("0"));
    StaticBox14 = new wxStaticBox(this, ID_STATICBOX14, _("100 Euros:"), wxPoint(320,200), wxSize(96,88), 0, _T("ID_STATICBOX14"));
    SpinCtrl13 = new wxSpinCtrl(this, ID_SPINCTRL13, _T("0"), wxPoint(224,224), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL13"));
    SpinCtrl13->SetValue(_T("0"));
    SpinCtrl12 = new wxSpinCtrl(this, ID_SPINCTRL12, _T("0"), wxPoint(120,224), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL12"));
    SpinCtrl12->SetValue(_T("0"));
    SpinCtrl11 = new wxSpinCtrl(this, ID_SPINCTRL11, _T("0"), wxPoint(16,224), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL11"));
    SpinCtrl11->SetValue(_T("0"));
    SpinCtrl10 = new wxSpinCtrl(this, ID_SPINCTRL10, _T("0"), wxPoint(432,128), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL10"));
    SpinCtrl10->SetValue(_T("0"));
    StaticBox13 = new wxStaticBox(this, ID_STATICBOX13, _("50 Euros:"), wxPoint(216,200), wxSize(96,88), 0, _T("ID_STATICBOX13"));
    StaticBox12 = new wxStaticBox(this, ID_STATICBOX12, _("20 Euros:"), wxPoint(112,200), wxSize(96,88), 0, _T("ID_STATICBOX12"));
    StaticBox11 = new wxStaticBox(this, ID_STATICBOX11, _("10 Euros:"), wxPoint(8,200), wxSize(96,88), 0, _T("ID_STATICBOX11"));
    StaticBox10 = new wxStaticBox(this, ID_STATICBOX10, _("10 Euros:"), wxPoint(424,104), wxSize(96,88), 0, _T("ID_STATICBOX10"));
    SpinCtrl1 = new wxSpinCtrl(this, ID_SPINCTRL1, _T("0"), wxPoint(16,32), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL1"));
    SpinCtrl1->SetValue(_T("0"));
    StaticBox8 = new wxStaticBox(this, ID_STATICBOX8, _("2 Euros:"), wxPoint(216,104), wxSize(96,88), 0, _T("ID_STATICBOX8"));
    StaticBox9 = new wxStaticBox(this, ID_STATICBOX9, _("5 Euros:"), wxPoint(320,104), wxSize(96,88), 0, _T("ID_STATICBOX9"));
    StaticBox1 = new wxStaticBox(this, ID_STATICBOX1, _("One cent:"), wxPoint(8,8), wxSize(96,88), 0, _T("ID_STATICBOX1"));
    StaticBox7 = new wxStaticBox(this, ID_STATICBOX7, _("1 Euro:"), wxPoint(112,104), wxSize(96,88), 0, _T("ID_STATICBOX7"));
    StaticBox6 = new wxStaticBox(this, ID_STATICBOX2, _("50 cents:"), wxPoint(8,104), wxSize(96,88), 0, _T("ID_STATICBOX2"));
    StaticBox5 = new wxStaticBox(this, ID_STATICBOX6, _("20 cents:"), wxPoint(424,8), wxSize(96,88), 0, _T("ID_STATICBOX6"));
    StaticBox4 = new wxStaticBox(this, ID_STATICBOX5, _("Ten cents:"), wxPoint(320,8), wxSize(96,88), 0, _T("ID_STATICBOX5"));
    StaticBox3 = new wxStaticBox(this, ID_STATICBOX4, _("Five cents:"), wxPoint(216,8), wxSize(96,88), 0, _T("ID_STATICBOX4"));
    StaticBox2 = new wxStaticBox(this, ID_STATICBOX3, _("Two cents:"), wxPoint(112,8), wxSize(96,88), 0, _T("ID_STATICBOX3"));
    sumLabel = new wxStaticText(this, ID_STATICTEXT1, _("Sum:"), wxPoint(160,336), wxDefaultSize, 0, _T("ID_STATICTEXT1"));
    sumField = new wxTextCtrl(this, ID_TEXTCTRL1, _("0"), wxPoint(208,336), wxSize(80,24), wxTE_READONLY|wxNO_BORDER, wxDefaultValidator, _T("ID_TEXTCTRL1"));
    SpinCtrl9 = new wxSpinCtrl(this, ID_SPINCTRL9, _T("0"), wxPoint(328,128), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL9"));
    SpinCtrl9->SetValue(_T("0"));
    SpinCtrl8 = new wxSpinCtrl(this, ID_SPINCTRL8, _T("0"), wxPoint(224,128), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL8"));
    SpinCtrl8->SetValue(_T("0"));
    SpinCtrl7 = new wxSpinCtrl(this, ID_SPINCTRL7, _T("0"), wxPoint(120,128), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL7"));
    SpinCtrl7->SetValue(_T("0"));
    SpinCtrl6 = new wxSpinCtrl(this, ID_SPINCTRL6, _T("0"), wxPoint(16,128), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL6"));
    SpinCtrl6->SetValue(_T("0"));
    SpinCtrl5 = new wxSpinCtrl(this, ID_SPINCTRL5, _T("0"), wxPoint(432,32), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL5"));
    SpinCtrl5->SetValue(_T("0"));
    SpinCtrl4 = new wxSpinCtrl(this, ID_SPINCTRL4, _T("0"), wxPoint(328,32), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL4"));
    SpinCtrl4->SetValue(_T("0"));
    SpinCtrl3 = new wxSpinCtrl(this, ID_SPINCTRL3, _T("0"), wxPoint(224,32), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL3"));
    SpinCtrl3->SetValue(_T("0"));
    SpinCtrl2 = new wxSpinCtrl(this, ID_SPINCTRL2, _T("0"), wxPoint(120,32), wxSize(80,25), 0, 0, 100000, 0, _T("ID_SPINCTRL2"));
    SpinCtrl2->SetValue(_T("0"));
    StaticBox15 = new wxStaticBox(this, ID_STATICBOX15, _("200 Euros:"), wxPoint(424,200), wxSize(96,88), 0, _T("ID_STATICBOX15"));
    MenuBar1 = new wxMenuBar();
    Menu1 = new wxMenu();
    MenuItem1 = new wxMenuItem(Menu1, idMenuQuit, _("Quit\tAlt-F4"), _("Quit the application"), wxITEM_NORMAL);
    Menu1->Append(MenuItem1);
    MenuBar1->Append(Menu1, _("File"));
    Menu2 = new wxMenu();
    MenuItem2 = new wxMenuItem(Menu2, ID_MENUITEM1, _("Select currency"), wxEmptyString, wxITEM_NORMAL);
    Menu2->Append(MenuItem2);
    MenuBar1->Append(Menu2, _("Settings"));
    SetMenuBar(MenuBar1);
    StatusBar1 = new wxStatusBar(this, ID_STATUSBAR1, 0, _T("ID_STATUSBAR1"));
    int __wxStatusBarWidths_1[1] = { -1 };
    int __wxStatusBarStyles_1[1] = { wxSB_NORMAL };
    StatusBar1->SetFieldsCount(1,__wxStatusBarWidths_1);
    StatusBar1->SetStatusStyles(1,__wxStatusBarStyles_1);
    SetStatusBar(StatusBar1);

    Connect(ID_SPINCTRL16,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL15,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL14,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL13,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL12,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL11,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL10,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL1,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL9,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL8,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL7,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL6,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL5,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL4,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL3,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(ID_SPINCTRL2,wxEVT_COMMAND_SPINCTRL_UPDATED,(wxObjectEventFunction)&MoneyCounterFrame::OnSpinChange);
    Connect(idMenuQuit,wxEVT_COMMAND_MENU_SELECTED,(wxObjectEventFunction)&MoneyCounterFrame::OnQuit);
    //*)
    moneyType = 0;
    weight[0] = 0.01;
    weight[1] = 0.02;
    weight[2] = 0.05;
    weight[3] = 0.1;
    weight[4] = 0.2;
    weight[5] = 0.5;
    weight[6] = 1.0;
    weight[7] = 2.0;
    weight[8] = 5.0;
    weight[9] = 10.0;
    //Setup config
    config = new wxConfig(wxT("MoneyCounter"));
    configName = "";
}

MoneyCounterFrame::~MoneyCounterFrame()
{
    //(*Destroy(MoneyCounterFrame)
    //*)
}

void MoneyCounterFrame::OnQuit(wxCommandEvent& event)
{
    saveData();
    Close();
}

void MoneyCounterFrame::OnAbout(wxCommandEvent& event)
{
    wxString msg = wxbuildinfo(long_f);
    wxMessageBox(msg, _("Welcome to..."));
}

void MoneyCounterFrame::setLabels()
{
    switch(moneyType)
    {
        case 0:
            {
                StaticBox8->SetLabel(_("2 Euros:"));
                StaticBox9->SetLabel(_("5 Euros:"));
                StaticBox10->SetLabel(_("10 Euros:"));
                StaticBox1->SetLabel(_("One cent:"));
                StaticBox7->SetLabel(_("1 Euro:"));
                StaticBox6->SetLabel(_("50 cents:"));
                StaticBox5->SetLabel(_("20 cents:"));
                StaticBox4->SetLabel(_("Ten cents:"));
                StaticBox3->SetLabel(_("Five cents:"));
                StaticBox2->SetLabel(_("Two cents:"));
                break;
                weight[0] = 0.01;
                weight[1] = 0.02;
                weight[2] = 0.05;
                weight[3] = 0.1;
                weight[4] = 0.2;
                weight[5] = 0.5;
                weight[6] = 1.0;
                weight[7] = 2.0;
                weight[8] = 5.0;
                weight[9] = 10.0;
            }
        case 1:
            {
                StaticBox8->SetLabel(_("2 Dollars:"));
                StaticBox9->SetLabel(_("5 Dollars:"));
                StaticBox10->SetLabel(_("10 Dollars:"));
                StaticBox1->SetLabel(_("One Dollar Cent:"));
                StaticBox7->SetLabel(_("One Dollar:"));
                StaticBox6->SetLabel(_("50 Dollar cents:"));
                StaticBox5->SetLabel(_("20 Dollar cents:"));
                StaticBox4->SetLabel(_("Ten Dollar cents:"));
                StaticBox3->SetLabel(_("Five Dollar cents:"));
                StaticBox2->SetLabel(_("Two Dollar cents:"));
                weight[0] = 0.01;
                weight[1] = 0.02;
                weight[2] = 0.05;
                weight[3] = 0.1;
                weight[4] = 0.2;
                weight[5] = 0.5;
                weight[6] = 1.0;
                weight[7] = 2.0;
                weight[8] = 5.0;
                weight[9] = 10.0;
                break;
            }

        default:break;

    }

}

void MoneyCounterFrame::OnSpinChange(wxSpinEvent& event)
{
    double sum = 0;
    values[0] = SpinCtrl1->GetValue();
    values[1] = SpinCtrl2->GetValue();
    values[2] = SpinCtrl3->GetValue();
    values[3] = SpinCtrl4->GetValue();
    values[4] = SpinCtrl5->GetValue();
    values[5] = SpinCtrl6->GetValue();
    values[6] = SpinCtrl7->GetValue();
    values[7] = SpinCtrl8->GetValue();
    values[8] = SpinCtrl9->GetValue();
    values[9] = SpinCtrl10->GetValue();
    values[10] = SpinCtrl11->GetValue();
    values[11] = SpinCtrl12->GetValue();
    values[12] = SpinCtrl13->GetValue();
    values[13] = SpinCtrl14->GetValue();
    values[14] = SpinCtrl15->GetValue();
    values[15] = SpinCtrl16->GetValue();
    BOOST_FOREACH(int v, values)
    {
        sum += values[i] * weight[i];
    }
    setText(sumField, sum);
}

void MoneyCounterFrame::saveData()
{
    if(configName != "") {config->SetPath(stringToWxString(configName));}
    config->Write(wxT("MoneyType"),moneyType);
    for(int i = 0;i<NUMFIELDS;i++)
        {
            config->Write(xToWxString(i), values[i]);
        }
}

void MoneyCounterFrame::readData()
{

    if(configName != "") {config->SetPath(stringToWxString(configName));}
    config->Read(wxT("MoneyType"),&moneyType);
    for(int i = 0;i<NUMFIELDS;i++)
        {
            config->Read(xToWxString(i), &values[i]);
        }
}
