package org.campware.cream.modules.screens;

/* ====================================================================
 * Copyright (C) 2003-2005  Media Development Loan Fund
 *
 *  * contact: contact@campware.org - http://www.campware.org
 * Campware encourages further development. Please let us know.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

import java.util.List;
import java.util.Date;
import java.util.Calendar;

import org.apache.turbine.util.RunData;

import org.apache.torque.util.Criteria;
import org.campware.cream.om.OnlineSubscriptionPeer;
import org.campware.cream.om.CustomerPeer;
import org.campware.cream.om.ProductPeer;
import org.campware.cream.om.SorderPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class OnlineSubscriptionList extends CreamList
{

    protected void initScreen()
    {
        setModuleType(DOCUMENT);
        setModuleName("ONLINE_SUBSCRIPTION");
        setIdName(OnlineSubscriptionPeer.ONLINE_SUBS_ID);
        setDefOrderColumn(OnlineSubscriptionPeer.ISSUED_DATE);
    }

    protected String getSortColumn(int sortNo)
    {
        if (sortNo==1){
            return OnlineSubscriptionPeer.ONLINE_SUBS_CODE;
        } else if (sortNo==2){
            return CustomerPeer.CUSTOMER_DISPLAY;
        } else if (sortNo==3){
            return SorderPeer.SORDER_CODE;
        } else if (sortNo==4){
            return ProductPeer.PRODUCT_DISPLAY;
        } else if (sortNo==5){
            return OnlineSubscriptionPeer.ISSUED_DATE;
        }
        
        return "";
    }

    protected void setFilter(int filterNo, Criteria listCriteria, RunData data)
    {
        int nowDay, nowMonth, nowYear;
        
        Calendar rightNow = Calendar.getInstance();
        nowMonth= rightNow.get(Calendar.MONTH);
        nowYear= rightNow.get(Calendar.YEAR);

        rightNow.set(nowYear, nowMonth, 1);
        Date fDayThisMonth= rightNow.getTime();
        rightNow.set(nowYear, nowMonth + 1, 1);
        Date fDayNextMonth= rightNow.getTime();
        rightNow.set(nowYear, nowMonth - 1, 1);
        Date fDayLastMonth= rightNow.getTime();
        rightNow.set(nowYear, 1, 1);
        Date fDayThisYear= rightNow.getTime();
        rightNow.set(nowYear + 1, 1, 1);
        Date fDayNextYear= rightNow.getTime();
        rightNow.set(nowYear - 1, 1, 1);
        Date fDayLastYear= rightNow.getTime();

        try
        {
            if (filterNo==1001){
                listCriteria.add(OnlineSubscriptionPeer.ISSUED_DATE, new Date(), Criteria.EQUAL);
            } else if (filterNo==1002){
                Criteria.Criterion b1 = listCriteria.getNewCriterion(OnlineSubscriptionPeer.ISSUED_DATE, fDayThisMonth, Criteria.GREATER_EQUAL);
                Criteria.Criterion b2 = listCriteria.getNewCriterion(OnlineSubscriptionPeer.ISSUED_DATE, fDayNextMonth, Criteria.LESS_THAN);
                listCriteria.add( b1.and( b2));
            } else if (filterNo==1003){
                Criteria.Criterion b1 = listCriteria.getNewCriterion(OnlineSubscriptionPeer.ISSUED_DATE, fDayLastMonth, Criteria.GREATER_EQUAL);
                Criteria.Criterion b2 = listCriteria.getNewCriterion(OnlineSubscriptionPeer.ISSUED_DATE, fDayThisMonth, Criteria.LESS_THAN);
                listCriteria.add( b1.and( b2));
            } else if (filterNo==1004){
                Criteria.Criterion b1 = listCriteria.getNewCriterion(OnlineSubscriptionPeer.ISSUED_DATE, fDayThisYear, Criteria.GREATER_EQUAL);
                Criteria.Criterion b2 = listCriteria.getNewCriterion(OnlineSubscriptionPeer.ISSUED_DATE, fDayNextYear, Criteria.LESS_THAN);
                listCriteria.add( b1.and( b2));
            } else if (filterNo==1005){
                Criteria.Criterion b1 = listCriteria.getNewCriterion(OnlineSubscriptionPeer.ISSUED_DATE, fDayLastYear, Criteria.GREATER_EQUAL);
                Criteria.Criterion b2 = listCriteria.getNewCriterion(OnlineSubscriptionPeer.ISSUED_DATE, fDayThisYear, Criteria.LESS_THAN);
                listCriteria.add( b1.and( b2));
            } else if (filterNo==1006){
                listCriteria.add(OnlineSubscriptionPeer.STATUS, new Integer(10), Criteria.EQUAL);
            } else if (filterNo==1007){
                listCriteria.add(OnlineSubscriptionPeer.STATUS, new Integer(30), Criteria.EQUAL);
            } else if (filterNo==1008){
                listCriteria.add(OnlineSubscriptionPeer.STATUS, new Integer(50), Criteria.EQUAL);
            } else if (filterNo==1009){
                listCriteria.add(OnlineSubscriptionPeer.CREATED_BY, (Object) data.getUser().getName(), Criteria.EQUAL);
            }
        }
        catch (Exception e)
        {
        }
    }

    protected void setFind(String findStr, Criteria listCriteria)
    {
        try
        {
            listCriteria.add(OnlineSubscriptionPeer.ONLINE_SUBS_CODE, (Object)findStr, Criteria.LIKE);
        }
        catch (Exception e)
        {
        }
    }

    protected List getEntries(Criteria criteria)
    {
        try
        {
            criteria.addJoin(CustomerPeer.CUSTOMER_ID, OnlineSubscriptionPeer.CUSTOMER_ID);
            criteria.addJoin(ProductPeer.PRODUCT_ID, OnlineSubscriptionPeer.PRODUCT_ID);
            criteria.addJoin(SorderPeer.SORDER_ID, OnlineSubscriptionPeer.SORDER_ID);
            return OnlineSubscriptionPeer.doSelect(criteria);
        }
        catch (Exception e)
        {
            return null;
        }
    }

}
