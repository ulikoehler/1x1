///Defines
#define NUMFIELDS 16 //Number of counter parameter fields
#define APPNAME MoneyCounter
///Macros
#define getIntValue(x) lexical_cast<int>(x->GetValue().mb_str())
#define xToWxString(x) wxString(lexical_cast<string>(x).c_str(),wxConvUTF8)
#define stringToWxString(x) wxString(x.c_str(), wxConvUTF8)
#define setText(x,y) x->SetValue(wxString(lexical_cast<string>(y).c_str(), wxConvUTF8))
