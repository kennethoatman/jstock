/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2013 Yan Cheng Cheok <yccheok@yahoo.com>
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

package org.yccheok.jstock.portfolio;

import org.yccheok.jstock.engine.SimpleDate;
import org.yccheok.jstock.engine.Code;

/**
 *
 * @author yccheok
 */
public class Dividend implements Commentable {
    public Dividend(Code code, double amount, SimpleDate date) {
        this.code = code;
        this.amount = amount;
        this.date = date;
    }

    public Dividend(Dividend dividend) {
        this.code = dividend.code;
        this.amount = dividend.amount;
        this.date = dividend.date;
        this.comment = dividend.comment;
    }

    public Dividend setCode(Code code) {
        Dividend dividend = new Dividend(code, this.amount, this.date);
        dividend.setComment(this.comment);
        return dividend;
    }

    public Dividend setAmount(double amount) {
        Dividend dividend = new Dividend(this.code, amount, this.date);
        dividend.setComment(this.comment);
        return dividend;
    }

    public Dividend setDate(SimpleDate date) {
        Dividend dividend = new Dividend(this.code, this.amount, date);
        dividend.setComment(this.comment);
        return dividend;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String getComment() {
        return this.comment;
    }

    public final Code code;
    public final double amount;
    public final SimpleDate date;
    private String comment = "";
}
