/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2013 Yan Cheng CHEOK <yccheok@yahoo.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package org.yccheok.jstock.engine;

/**
 * A pair which carries code and symbol information of a stock. The reason we
 * do not inherited from Pair class is that, we do not like getFirst method name
 * and getSecond name, which is less readable.
 * @author yccheok
 */
public class StockInfo {
    /**
     * Code of this stock info.
     */
    public final Code code;
    
    private final String name;

    private final Board board;
    
    private final Industry industry;

    private final boolean userDefined;
   
    public static StockInfo newInstance(Code code) {
        return new StockInfo(code);
    }
    
    public static StockInfo newInstance(Code code, String name) {
        return new StockInfo(code, name);
    }

    public static StockInfo newInstance(Code code, String name, Industry industry, Board board, boolean user) {
        return new StockInfo(code, name, industry, board, user);
    }
    
    // Cannot be private, caused by inheritance requirement by StockInfoWithSymbolAsString
    /**
     * Constructs an instance of stock info.
     * @param code the code
     * @param symbol the symbol
     */
    public StockInfo(Code code) {
        if (code == null) {
            throw new java.lang.IllegalArgumentException("code cannot be null");
        }
        this.code = code;
        this.name = "";
        this.board = Board.Unknown;
        this.industry = Industry.Unknown;
        this.userDefined = false;
    }

    public StockInfo(Code code, String name) {
        if (code == null) {
            throw new java.lang.IllegalArgumentException("code cannot be null");
        }
        this.code = code;
        this.name = name;
        this.board = Board.Unknown;
        this.industry = Industry.Unknown;
        this.userDefined = false;
    }
    
      public StockInfo(Code code, String name, Industry industry, Board board, boolean userDefined) {
        if (code == null) {
            throw new java.lang.IllegalArgumentException("code cannot be null");
        }
        this.code = code;
        this.name = name;
        this.board = board;
        this.industry = industry;
        this.userDefined = userDefined;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof StockInfo))
            return false;

        StockInfo stockInfo = (StockInfo)o;
        return this.code.equals(stockInfo.code);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.code.hashCode();
        return hash;
    }

    public String getName() {
        return this.name;
    }
    
    public Industry getIndustry() {
        return this.industry;
    }
    
    public Board getBoard() {
        return this.board;
    }
    
    public boolean getUserDefined() {
        return this.userDefined;
    }
    
    // A stock info which its toString will return code.
    // Having a correct implementation of toString is important as :
    // 1) Our search engine build the key index through toString.
    // 2) Our auto complete combo box display its drop down list items based on
    //    toString.
    @Override
    public String toString() {
        return this.code.toString();
    }
    
        public enum Board {
                                                // The following are naming conventions from CIMB :
        Main("Main Board"),                     // Main
        Second("Second Board"),                 // 2nd
        Mesdaq("Mesdaq"),                       // MESDAQ
        CallWarrant("Call Warrant"),            // ??
        KualaLumpur("Kuala Lumpur"),
        SES("SES"),                             // Singapore
        Copenhagen("Copenhagen"),               // Denmark        
        Paris("Paris"),                         // France
        Xetra("Xetra"),                         // Germany
        XETRA("XETRA"),
        Munich("Munich"),
        Stuttgart("Stuttgart"),
        Berlin("Berlin"),
        Hamburg("Hamburg"),
        Dusseldorf("Dusseldorf"),
        Frankfurt("Frankfurt"),
        Hannover("Hannover"),
        Milan("Milan"),                         // Italy
        Oslo("Oslo"),                           // Norway
        Madrid("Madrid"),                       // Spain
        MCE("MCE"),
        MercadoContinuo("Mercado Continuo"),
        Stockholm("Stockholm"),                 // Sweden
        FSI("FSI"),                             // UK
        London("London"),
        NasdaqSC("NasdaqSC"),                   // US
        DJI("DJI"),
        NasdaqNM("NasdaqNM"),
        NYSE("NYSE"),
        Nasdaq("Nasdaq"),
        AMEX("AMEX"),
        PinkSheet("Pink Sheet"),
        Sydney("Sydney"),                       // Australia
        ASX("ASX"),
        Vienna("Vienna"),                       // Austria
        Brussels("Brussels"),                   // Belgium
        Toronto("Toronto"),                     // Canada
        HKSE("HKSE"),                           // Hong Kong
        Jakarta("Jakarta"),                     // Indonesia
        KSE("KSE"),                             // Korea
        KOSDAQ("KOSDAQ"),
        Amsterdam("Amsterdam"),                 // Netherlands
        ENX("ENX"),                             // Portugal
        Lisbon("Lisbon"),
        VTX("VTX"),                             // Switzerland
        Virt_X("Virt-X"),
        Switzerland("Switzerland"),
        Taiwan("Taiwan"),                       // Taiwan
        BOM("Bombay"),                          // India
        NSE("NSE"),

        NZSX("NZ Stock Market"),                // New Zealand
        NZDX("NZ Debt Market"),
        NZAX("NZ Alternative Market"),
        
        TASE("Tel Aviv Stock Exchange"),        // Israel
        
        UserDefined("User Defined"),
        Unknown("Unknown");
        
        private final String name;

        public static Board newInstance(String board) {
            return Board.valueOf(board);
        }
        
        Board(String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return name;
        }
        
        public String toOriginalString() {
            return super.toString();
        }        
    }
        
    public enum Industry {
                                                    // The following are naming conventions from CIMB :
        ConsumerProducts("Consumer Products"),      // CONSUMER
        IndustrialProducts("Industrial Products"),  // IND-PROD
        Construction("Construction"),               // CONSTRUCTN
        TradingServices("Trading / Services"),      // TRAD/SERV
        Technology("Technology"),                   // TECHNOLOGY
        Infrastructure("Infrastructure"),           // IPC
        Finance("Finance"),                         // FINANCE
        Hotels("Hotels"),                           // HOTELS
        Properties("Properties"),                   // PROPERTIES 
        Plantation("Plantation"),                   // PLANTATION
        Mining("Mining"),                           // MINING
        Trusts("Trusts"),                           // REITS
        CloseEndFund("Close-End Fund"),             // CLOSED/FUND 
        ETF("ETF"),                                 // ETF
        Loans("Loans"),                             // LOANS
        CallWarrant("Call Warrant"),                // CALL-WARR
        UserDefined("User Defined"),
        Unknown("Unknown");
        
        private final String name;

        public static Industry newInstance(String board) {
            return Industry.valueOf(board);
        }
        
        Industry(String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return name;
        }
        
        public String toOriginalString() {
            return super.toString();
        }
    }
    
    
}
