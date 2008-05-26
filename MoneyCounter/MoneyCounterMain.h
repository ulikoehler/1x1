/***************************************************************
 * Name:      MoneyCounterMain.h
 * Purpose:   Defines Application Frame
 * Author:    Uli Koehler ()
 * Created:   2008-05-11
 * Copyright: Uli Koehler ()
 * License:
 **************************************************************/

#ifndef MONEYCOUNTERMAIN_H
#define MONEYCOUNTERMAIN_H

//(*Headers(MoneyCounterFrame)
#include <wx/stattext.h>
#include <wx/menu.h>
#include <wx/textctrl.h>
#include <wx/spinctrl.h>
#include <wx/statbox.h>
#include <wx/frame.h>
#include <wx/statusbr.h>
//*)
///User includes
#include <string>
#include <boost/lexical_cast.hpp>
#include <boost/foreach.hpp>
#include <wx/config.h>
//Local includes
#include "Currency.h"
#include "Macros.h"
//Namespace usages
using namespace boost;
using namespace std;

class MoneyCounterFrame: public wxFrame
{
    public:

        MoneyCounterFrame(wxWindow* parent,wxWindowID id = -1);
        virtual ~MoneyCounterFrame();

    private:

        //(*Handlers(MoneyCounterFrame)
        void OnQuit(wxCommandEvent& event);
        void OnAbout(wxCommandEvent& event);
        void OnSpinChange(wxSpinEvent& event);
        //*)

        //(*Identifiers(MoneyCounterFrame)
        static const long ID_SPINCTRL16;
        static const long ID_STATICBOX16;
        static const long ID_SPINCTRL15;
        static const long ID_SPINCTRL14;
        static const long ID_STATICBOX14;
        static const long ID_SPINCTRL13;
        static const long ID_SPINCTRL12;
        static const long ID_SPINCTRL11;
        static const long ID_SPINCTRL10;
        static const long ID_STATICBOX13;
        static const long ID_STATICBOX12;
        static const long ID_STATICBOX11;
        static const long ID_STATICBOX10;
        static const long ID_SPINCTRL1;
        static const long ID_STATICBOX8;
        static const long ID_STATICBOX9;
        static const long ID_STATICBOX1;
        static const long ID_STATICBOX7;
        static const long ID_STATICBOX2;
        static const long ID_STATICBOX6;
        static const long ID_STATICBOX5;
        static const long ID_STATICBOX4;
        static const long ID_STATICBOX3;
        static const long ID_STATICTEXT1;
        static const long ID_TEXTCTRL1;
        static const long ID_SPINCTRL9;
        static const long ID_SPINCTRL8;
        static const long ID_SPINCTRL7;
        static const long ID_SPINCTRL6;
        static const long ID_SPINCTRL5;
        static const long ID_SPINCTRL4;
        static const long ID_SPINCTRL3;
        static const long ID_SPINCTRL2;
        static const long ID_STATICBOX15;
        static const long idMenuQuit;
        static const long ID_MENUITEM1;
        static const long ID_STATUSBAR1;
        //*)

        //(*Declarations(MoneyCounterFrame)
        wxSpinCtrl* SpinCtrl4;
        wxStaticBox* StaticBox8;
        wxStaticBox* StaticBox5;
        wxStaticBox* StaticBox11;
        wxSpinCtrl* SpinCtrl1;
        wxStaticBox* StaticBox7;
        wxSpinCtrl* SpinCtrl15;
        wxSpinCtrl* SpinCtrl13;
        wxSpinCtrl* SpinCtrl9;
        wxSpinCtrl* SpinCtrl16;
        wxSpinCtrl* SpinCtrl7;
        wxStaticBox* StaticBox6;
        wxSpinCtrl* SpinCtrl12;
        wxStaticBox* StaticBox1;
        wxStaticBox* StaticBox9;
        wxSpinCtrl* SpinCtrl14;
        wxStaticBox* StaticBox13;
        wxStaticBox* StaticBox2;
        wxSpinCtrl* SpinCtrl3;
        wxStatusBar* StatusBar1;
        wxStaticBox* StaticBox15;
        wxStaticBox* StaticBox12;
        wxSpinCtrl* SpinCtrl10;
        wxStaticText* sumLabel;
        wxSpinCtrl* SpinCtrl2;
        wxStaticBox* StaticBox3;
        wxSpinCtrl* SpinCtrl5;
        wxStaticBox* StaticBox14;
        wxTextCtrl* sumField;
        wxSpinCtrl* SpinCtrl11;
        wxStaticBox* StaticBox10;
        wxSpinCtrl* SpinCtrl6;
        wxStaticBox* StaticBox16;
        wxStaticBox* StaticBox4;
        wxSpinCtrl* SpinCtrl8;
        //*)

        //User defined variables
        Currency moneyType*; //0=Euro;1=Dollar

        int values[NUMFIELDS];
        wxConfig* config;
        string configName;

        //User defined functions
        void setLabels();
        void saveData();
        void readData();

        DECLARE_EVENT_TABLE()
};


#endif // MONEYCOUNTERMAIN_H
