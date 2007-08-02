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

import java.util.ArrayList;
import java.util.List;

import org.apache.torque.util.BasePeer;
import org.apache.torque.util.Criteria;

import org.apache.velocity.context.Context;

import org.campware.cream.om.ContactPeer;
import org.campware.cream.om.Opportunity;
import org.campware.cream.om.PaymentItemPeer;
import org.campware.cream.om.Sorder;
import org.campware.cream.om.SorderPeer;
import org.campware.cream.om.OpportunityPeer;
import org.campware.cream.om.ProductPeer;
import org.campware.cream.om.ProjectPeer;
import org.campware.cream.om.CarrierPeer;
import org.campware.cream.om.CustomerPeer;
import org.campware.cream.om.CurrencyPeer;
import org.campware.cream.om.SorderDocPeer;

import com.workingdogs.village.Record;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class OrderForm extends CreamForm
{
    protected void initScreen()
    {
        setModuleType(DOCUMENT);
        setModuleName("SORDER");
        setIdName(SorderPeer.SORDER_ID);
        setFormIdName("sorderid");
    }

    protected boolean getEntry(Criteria criteria, Context context)
    {
        try
        {
            Sorder entry = (Sorder) SorderPeer.doSelect(criteria).get(0);
            context.put("entry", entry);
            context.put("entryitems", entry.getSorderItems());

            Criteria prodcrit = new Criteria();
            prodcrit.add(ProductPeer.PRODUCT_ID, 999, Criteria.GREATER_THAN);
            prodcrit.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
            context.put("products", ProductPeer.doSelect(prodcrit));

            Criteria projcrit = new Criteria();
            Criteria.Criterion pj1 = projcrit.getNewCriterion(ProjectPeer.PROJECT_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion pj2 = projcrit.getNewCriterion(ProjectPeer.STATUS, new Integer(30), Criteria.EQUAL);
            Criteria.Criterion pj3 = projcrit.getNewCriterion(ProjectPeer.PROJECT_ID, new Integer(entry.getProjectId()), Criteria.EQUAL);
            projcrit.add( pj1.or(pj2.or(pj3)));
            projcrit.addAscendingOrderByColumn(ProjectPeer.PROJECT_NAME);
            context.put("projects", ProjectPeer.doSelect(projcrit));

            Criteria custcrit = new Criteria();
            Criteria.Criterion cu1 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion cu2 = custcrit.getNewCriterion(CustomerPeer.STATUS, new Integer(30), Criteria.EQUAL);
            Criteria.Criterion cu3 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(entry.getCustomerId()), Criteria.EQUAL);
            Criteria.Criterion cu4 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(entry.getRecipientId()), Criteria.EQUAL);
            custcrit.add( cu1.or(cu2.or(cu3.or(cu4))));
            custcrit.addAscendingOrderByColumn(CustomerPeer.CUSTOMER_DISPLAY);
            context.put("customers", CustomerPeer.doSelect(custcrit));

            Criteria oppcrit = new Criteria();
            Criteria.Criterion op1 = oppcrit.getNewCriterion(OpportunityPeer.OPPORTUNITY_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion op2 = oppcrit.getNewCriterion(OpportunityPeer.OPPORTUNITY_ID, new Integer(entry.getOpportunityId()), Criteria.EQUAL);
            Criteria.Criterion op3 = oppcrit.getNewCriterion(OpportunityPeer.CUSTOMER_ID, new Integer(entry.getCustomerId()), Criteria.EQUAL);
            Criteria.Criterion op4 = oppcrit.getNewCriterion(OpportunityPeer.STATUS, new Integer(30), Criteria.EQUAL);
            oppcrit.add( op1.or( op2.or(op3.and(op4))));
            oppcrit.addAscendingOrderByColumn(OpportunityPeer.OPPORTUNITY_NAME);
            context.put("opportunities", OpportunityPeer.doSelect(oppcrit));

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
            Sorder entry = new Sorder();
            context.put("entry", entry);

			Criteria prodcrit = new Criteria();
            Criteria.Criterion pd1 = prodcrit.getNewCriterion(ProductPeer.PRODUCT_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion pd2 = prodcrit.getNewCriterion(ProductPeer.STATUS, new Integer(30), Criteria.EQUAL);
            prodcrit.add( pd1.or(pd2));
            prodcrit.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
            context.put("products", ProductPeer.doSelect(prodcrit));

            Criteria projcrit = new Criteria();
            Criteria.Criterion pj1 = projcrit.getNewCriterion(ProjectPeer.PROJECT_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion pj2 = projcrit.getNewCriterion(ProjectPeer.STATUS, new Integer(30), Criteria.EQUAL);
            projcrit.add( pj1.or(pj2));
            projcrit.addAscendingOrderByColumn(ProjectPeer.PROJECT_NAME);
            context.put("projects", ProjectPeer.doSelect(projcrit));

            Criteria custcrit = new Criteria();
            Criteria.Criterion cu1 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(1000), Criteria.EQUAL);
            Criteria.Criterion cu2 = custcrit.getNewCriterion(CustomerPeer.STATUS, new Integer(30), Criteria.EQUAL);
            custcrit.add( cu1.or(cu2));
            custcrit.addAscendingOrderByColumn(CustomerPeer.CUSTOMER_DISPLAY);
            context.put("customers", CustomerPeer.doSelect(custcrit));

            Criteria oppcrit = new Criteria();
            oppcrit.add(OpportunityPeer.OPPORTUNITY_ID, 1000, Criteria.EQUAL);
            context.put("opportunities", OpportunityPeer.doSelect(oppcrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

	protected boolean getNewRelated(int relform, int relid, Context context)
	{
		try
		{
			Sorder entry = new Sorder();

			if (relform==CUSTOMER){
				
				entry.setCustomerId(relid);
				entry.setRecipientId(relid);

				Criteria prodcrit = new Criteria();
	            Criteria.Criterion pd1 = prodcrit.getNewCriterion(ProductPeer.PRODUCT_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion pd2 = prodcrit.getNewCriterion(ProductPeer.STATUS, new Integer(30), Criteria.EQUAL);
	            prodcrit.add( pd1.or(pd2));
	            prodcrit.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
	            context.put("products", ProductPeer.doSelect(prodcrit));

	            Criteria projcrit = new Criteria();
	            Criteria.Criterion pj1 = projcrit.getNewCriterion(ProjectPeer.PROJECT_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion pj2 = projcrit.getNewCriterion(ProjectPeer.STATUS, new Integer(30), Criteria.EQUAL);
	            projcrit.add( pj1.or(pj2));
	            projcrit.addAscendingOrderByColumn(ProjectPeer.PROJECT_NAME);
	            context.put("projects", ProjectPeer.doSelect(projcrit));

	            Criteria custcrit = new Criteria();
	            Criteria.Criterion cu1 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion cu2 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(relid), Criteria.EQUAL);
	            custcrit.add( cu1.or(cu2));
	            custcrit.addAscendingOrderByColumn(CustomerPeer.CUSTOMER_DISPLAY);
	            context.put("customers", CustomerPeer.doSelect(custcrit));
		
	            Criteria oppcrit = new Criteria();
	            Criteria.Criterion op1 = oppcrit.getNewCriterion(OpportunityPeer.OPPORTUNITY_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion op2 = oppcrit.getNewCriterion(OpportunityPeer.CUSTOMER_ID, new Integer(relid), Criteria.EQUAL);
	            Criteria.Criterion op3 = oppcrit.getNewCriterion(OpportunityPeer.STATUS, new Integer(30), Criteria.EQUAL);
	            oppcrit.add( op1.or(op2.and(op3)));
	            oppcrit.addAscendingOrderByColumn(OpportunityPeer.OPPORTUNITY_NAME);
	            context.put("opportunities", OpportunityPeer.doSelect(oppcrit));

			}else if(relform==PROJECT){
				
				entry.setProjectId(relid);

				Criteria prodcrit = new Criteria();
	            Criteria.Criterion pd1 = prodcrit.getNewCriterion(ProductPeer.PRODUCT_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion pd2 = prodcrit.getNewCriterion(ProductPeer.STATUS, new Integer(30), Criteria.EQUAL);
	            prodcrit.add( pd1.or(pd2));
	            prodcrit.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);
	            context.put("products", ProductPeer.doSelect(prodcrit));

	            Criteria projcrit = new Criteria();
	            Criteria.Criterion pj1 = projcrit.getNewCriterion(ProjectPeer.PROJECT_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion pj2 = projcrit.getNewCriterion(ProjectPeer.PROJECT_ID, new Integer(relid), Criteria.EQUAL);
	            projcrit.add( pj1.or( pj2));
	            projcrit.addAscendingOrderByColumn(ProjectPeer.PROJECT_NAME);
	            context.put("projects", ProjectPeer.doSelect(projcrit));

	            Criteria custcrit = new Criteria();
	            Criteria.Criterion cu1 = custcrit.getNewCriterion(CustomerPeer.CUSTOMER_ID, new Integer(1000), Criteria.EQUAL);
	            Criteria.Criterion cu2 = custcrit.getNewCriterion(CustomerPeer.STATUS, new Integer(30), Criteria.EQUAL);
	            custcrit.add( cu1.or(cu2));
	            custcrit.addAscendingOrderByColumn(CustomerPeer.CUSTOMER_DISPLAY);
	            context.put("customers", CustomerPeer.doSelect(custcrit));
		
	            Criteria oppcrit = new Criteria();
	            oppcrit.add(OpportunityPeer.OPPORTUNITY_ID, 1000, Criteria.EQUAL);
	            oppcrit.addAscendingOrderByColumn(OpportunityPeer.OPPORTUNITY_NAME);
	            context.put("opportunities", OpportunityPeer.doSelect(oppcrit));

			}

			context.put("entry", entry);
            
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
            Criteria carrcrit = new Criteria();
            carrcrit.add(CarrierPeer.CARRIER_ID, 999, Criteria.GREATER_THAN);
            carrcrit.addAscendingOrderByColumn(CarrierPeer.CARRIER_NAME);
            context.put("carriers", CarrierPeer.doSelect(carrcrit));

            Criteria currcrit = new Criteria();
            currcrit.add(CurrencyPeer.CURRENCY_ID, 1000, Criteria.GREATER_THAN);
            currcrit.addAscendingOrderByColumn(CurrencyPeer.CURRENCY_CODE);
            context.put("currencies", CurrencyPeer.doSelect(currcrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
