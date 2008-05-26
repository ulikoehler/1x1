#ifndef CURRENCY_H
#define CURRENCY_H
#include "MoneyCounterMain.h" //Other includes are defined here
class Currency
{
 public:
    double weight[NUMFIELDS];
    string name;
    Currency();
    ~Currency();
    void storeInConfig();
    static vector<Currency*> instances;
 private:
    wxConfig *config;
    unsigned int instanceNr; //Element index of this instancy in instances vector
};
Currency::Currency()
    {
        config = new wxConfig();
        instances->push_back(&this);
        instanceNr = instances.size();
    }
Currency::~Currency()
    {
        instances.re
    }
Currency::storeInConfig()
    {
        config = new wxConfig(wxT(APPNAME));
        config->SetPath(wxT("/Currencies/" + name.c_str()));
        for(int i = 0;i<NUMFIELDS;i++)
            {
                config->Write(xToWxString(i), weight[i]);
            }
    }

#endif
