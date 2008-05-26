/***************************************************************
 * Name:      MoneyCounterApp.cpp
 * Purpose:   Code for Application Class
 * Author:    Uli Koehler ()
 * Created:   2008-05-11
 * Copyright: Uli Koehler ()
 * License:
 **************************************************************/

#include "wx_pch.h"
#include "MoneyCounterApp.h"

//(*AppHeaders
#include "MoneyCounterMain.h"
#include <wx/image.h>
//*)

IMPLEMENT_APP(MoneyCounterApp);

bool MoneyCounterApp::OnInit()
{
    //(*AppInitialize
    bool wxsOK = true;
    wxInitAllImageHandlers();
    if ( wxsOK )
    {
    	MoneyCounterFrame* Frame = new MoneyCounterFrame(0);
    	Frame->Show();
    	SetTopWindow(Frame);
    }
    //*)
    return wxsOK;

}
