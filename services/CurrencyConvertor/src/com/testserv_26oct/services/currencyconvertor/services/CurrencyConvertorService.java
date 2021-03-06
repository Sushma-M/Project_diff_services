/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

package com.testserv_26oct.services.currencyconvertor.services;
import com.testserv_26oct.services.currencyconvertor.*;
import java.util.List;

import com.wavemaker.runtime.soap.util.SoapSettingsResolver;
import com.wavemaker.runtime.soap.SoapServiceSettings;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import javax.xml.ws.BindingProvider;



@Service
public class CurrencyConvertorService{

    @Autowired
    @Qualifier("CurrencyConvertorSoapServiceSettings")
    private SoapServiceSettings soapServiceSettings;


	public ConversionRateResponse conversionRate(  com.testserv_26oct.services.currencyconvertor.ConversionRate parameters )
	          {
 	    CurrencyConvertor currencyconvertor = new CurrencyConvertor();
 CurrencyConvertorSoap currencyconvertorsoap = currencyconvertor.getCurrencyConvertorSoap();
 SoapSettingsResolver.setBindingProperties((BindingProvider)currencyconvertorsoap, soapServiceSettings);
 return currencyconvertorsoap.conversionRate(parameters );
	}
}
