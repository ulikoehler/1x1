/***************************************************************
 * Name:      GleichungstrainerApp.cpp
 * Purpose:   Code for Application Class
 * Author:    Uli Köhler ()
 * Created:   2007-12-07
 * Copyright: Uli Köhler ()
 * License:
 **************************************************************/

#include "wx_pch.h"
#include "GleichungstrainerApp.h"

//(*AppHeaders
#include "GleichungstrainerMain.h"
#include <wx/image.h>
//*)

IMPLEMENT_APP(GleichungstrainerApp);

bool GleichungstrainerApp::OnInit()
{
    //(*AppInitialize
    bool wxsOK = true;
    wxInitAllImageHandlers();
    if ( wxsOK )
    {
    	GleichungstrainerFrame* Frame = new GleichungstrainerFrame(0);
    	Frame->Show();
    	SetTopWindow(Frame);
    }
    //*)
    return wxsOK;

}
