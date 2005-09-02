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

import org.apache.torque.util.Criteria;

import org.apache.velocity.context.Context;

import org.campware.cream.om.OnlineSubscription;
import org.campware.cream.om.OnlineSubscriptionPeer;
import org.campware.cream.om.ProductPeer;
import org.campware.cream.om.ProjectPeer;
import org.campware.cream.om.CustomerPeer;
import org.campware.cream.om.SorderPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class OnlineSubscriptionForm extends CreamForm
{
    protected void initScreen()
    {
        setModuleType(DOCUMENT);
        setModuleName("ONLINE_SUBSCRIPTION");
        setIdName(OnlineSubscriptionPeer.ONLINE_SUBS_ID);
        setFormIdName("onlinesubsid");
    }

    protected boolean getEntry(Criteria criteria, Context context)
    {
        try
        {
            OnlineSubscription entry = (OnlineSubscription) OnlineSubscriptionPeer.doSelect(criteria).get(0);
            context.put("entry", entry);

            Criteria ordcrit = new Criteria();
            Criteria.Criterion a1 = ordcrit.getNewCriterion(SorderPeer.SORDER_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion a2 = ordcrit.getNewCriterion(SorderPeer.SORDER_ID, new Integer(entry.getSorderId()), Criteria.EQUAL);
            Criteria.Criterion a3 = ordcrit.getNewCriterion(SorderPeer.CUSTOMER_ID, new Integer(entry.getCustomerId()), Criteria.EQUAL);
            Criteria.Criterion a4 = ordcrit.getNewCriterion(SorderPeer.STATUS, new Integer(30), Criteria.EQUAL);
            ordcrit.add( a1.or( a2.or(a3.and(a4))));
            ordcrit.addAscendingOrderByColumn(SorderPeer.SORDER_CODE);
            context.put("orders", SorderPeer.doSelect(ordcrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    protected boolean getNew(Context context)
    {
        try
        {
            OnlineSubscription entry = new OnlineSubscription();
            context.put("entry", entry);

            Criteria ordcrit = new Criteria();
            ordcrit.add(SorderPeer.SORDER_ID, 1000, Criteria.EQUAL);
            context.put("orders", SorderPeer.doSelect(ordcrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    protected boolean getLookups(Context context)
    {
        try
        {
            Criteria prodcrit = new Criteria();
            Criteria.Criterion a1 = prodcrit.getNewCriterion(ProductPeer.PRODUCT_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion a2 = prodcrit.getNewCriterion(ProductPeer.PRODUCT_TYPE, new Integer(30), Criteria.EQUAL);
            prodcrit.add( a1.or( a2));
            prodcrit.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
            context.put("products", ProductPeer.doSelect(prodcrit));

            Criteria projcrit = new Criteria();
            projcrit.add(ProjectPeer.PROJECT_ID, 999, Criteria.GREATER_THAN);
            projcrit.addAscendingOrderByColumn(ProjectPeer.PROJECT_NAME);
            context.put("projects", ProjectPeer.doSelect(projcrit));

            Criteria custcrit = new Criteria();
            Criteria.Criterion b1 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion b2 = custcrit.getNewCriterion(CustomerPeer.STATUS, new Integer(29), Criteria.GREATER_THAN);
            custcrit.add( b1.or( b2));
            custcrit.addAscendingOrderByColumn(CustomerPeer.CUSTOMER_DISPLAY);
            context.put("customers", CustomerPeer.doSelect(custcrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


}
