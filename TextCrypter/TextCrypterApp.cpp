/***************************************************************
 * Name:      TextCrypterApp.cpp
 * Purpose:   Code for Application Class
 * Author:    Uli Koehler ()
 * Created:   2008-04-21
 * Copyright: Uli Koehler ()
 * License:
 **************************************************************/

#include "wx_pch.h"
#include "TextCrypterApp.h"

//(*AppHeaders
#include "TextCrypterMain.h"
#include <wx/image.h>
//*)
#include <botan/botan.h>
using namespace Botan;

IMPLEMENT_APP(TextCrypterApp);

bool TextCrypterApp::OnInit()
{
    //(*AppInitialize
    bool wxsOK = true;
    wxInitAllImageHandlers();
    if ( wxsOK )
    {
    	TextCrypterFrame* Frame = new TextCrypterFrame(0);
    	Frame->Show();
    	SetTopWindow(Frame);
    }
    //*)
    return wxsOK;

}
