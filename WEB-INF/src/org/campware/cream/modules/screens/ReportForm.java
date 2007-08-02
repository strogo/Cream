package org.campware.cream.modules.screens;

/* ====================================================================
 * Copyright (C) 2003  Media Development Loan Fund
 *
 * Contact: contact@campware.org - http://www.campware.org
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

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.apache.turbine.util.RunData;

import org.apache.torque.util.Criteria;

import org.apache.velocity.context.Context;

import org.campware.cream.om.CustomerPeer;
import org.campware.cream.om.ProductPeer;
import org.campware.cream.om.ProjectPeer;
import org.campware.cream.om.CustomerCategoryPeer;
import org.campware.cream.om.ProjectCategoryPeer;
import org.campware.cream.om.ProductCategoryPeer;
import org.campware.cream.om.CountryPeer;
import org.campware.cream.om.RegionPeer;
import org.campware.cream.om.LanguagePeer;
import org.campware.cream.om.IndustryPeer;
import org.campware.cream.om.LeadSourcePeer;
import org.campware.cream.om.VendorPeer;
import org.campware.cream.om.UomPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class ReportForm extends SecureScreen
{

    public void doBuildTemplate(RunData data, Context context)
    {
        try
        {
            
            context.put("df", new SimpleDateFormat ("dd.MM.yyyy"));
            context.put("dtf", new SimpleDateFormat ("dd.MM.yyyy hh:mm:ss"));
            
            DecimalFormatSymbols symb= new DecimalFormatSymbols();
            symb.setDecimalSeparator('.');
            
            context.put("af", new DecimalFormat ("0.00", symb));
            context.put("rf", new DecimalFormat ("0.000000", symb));
            context.put("today", new Date());

            Criteria prodcrit = new Criteria();
            prodcrit.add(ProductPeer.PRODUCT_ID, 999, Criteria.GREATER_THAN);
            prodcrit.addAscendingOrderByColumn(ProductPeer.PRODUCT_DISPLAY);
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

            Criteria custcatcrit = new Criteria();
            custcatcrit.add(CustomerCategoryPeer.CUSTOMER_CAT_ID, 999, Criteria.GREATER_THAN);
            custcatcrit.addAscendingOrderByColumn(CustomerCategoryPeer.CUSTOMER_CAT_NAME);
            context.put("customercats", CustomerCategoryPeer.doSelect(custcatcrit));

            Criteria countrycrit = new Criteria();
            countrycrit.add(CountryPeer.COUNTRY_ID, 999, Criteria.GREATER_THAN);
            countrycrit.addAscendingOrderByColumn(CountryPeer.COUNTRY_NAME);
            context.put("countries", CountryPeer.doSelect(countrycrit));

            Criteria regioncrit = new Criteria();
            regioncrit.add(RegionPeer.REGION_ID, 999, Criteria.GREATER_THAN);
            regioncrit.addAscendingOrderByColumn(RegionPeer.REGION_NAME);
            context.put("regions", RegionPeer.doSelect(regioncrit));

            Criteria langcrit = new Criteria();
            langcrit.add(LanguagePeer.LANGUAGE_ID, 999, Criteria.GREATER_THAN);
            langcrit.addAscendingOrderByColumn(LanguagePeer.LANGUAGE_NAME);
            context.put("languages", LanguagePeer.doSelect(langcrit));

            Criteria industrycrit = new Criteria();
            industrycrit.add(IndustryPeer.INDUSTRY_ID, 999, Criteria.GREATER_THAN);
            industrycrit.addAscendingOrderByColumn(IndustryPeer.INDUSTRY_NAME);
            context.put("industries", IndustryPeer.doSelect(industrycrit));

            Criteria leadsrccrit = new Criteria();
            leadsrccrit.add(LeadSourcePeer.LEAD_SOURCE_ID, 999, Criteria.GREATER_THAN);
            leadsrccrit.addAscendingOrderByColumn(LeadSourcePeer.LEAD_SOURCE_NAME);
            context.put("leadsources", LeadSourcePeer.doSelect(leadsrccrit));

            Criteria vendorcrit = new Criteria();
            vendorcrit.add(VendorPeer.VENDOR_ID, 999, Criteria.GREATER_THAN);
            vendorcrit.addAscendingOrderByColumn(VendorPeer.VENDOR_NAME);
            context.put("vendors", VendorPeer.doSelect(vendorcrit));

            Criteria uomcrit = new Criteria();
            uomcrit.add(UomPeer.UOM_ID, 900, Criteria.LESS_THAN);
            Criteria.Criterion criterion = uomcrit.getCriterion(UomPeer.UOM_ID);
            criterion.or(
                           uomcrit.getNewCriterion(
                                         criterion.getTable(),
                                         criterion.getColumn(),
                                         new Integer(999),
                                         Criteria.GREATER_THAN )
                           );
            uomcrit.addAscendingOrderByColumn(UomPeer.UOM_NAME);
            context.put("uoms", UomPeer.doSelect(uomcrit));

            Criteria prodcatcrit = new Criteria();
            prodcatcrit.add(ProductCategoryPeer.PRODUCT_CAT_ID, 999, Criteria.GREATER_THAN);
            prodcatcrit.addAscendingOrderByColumn(ProductCategoryPeer.PRODUCT_CAT_NAME);
            context.put("productcats", ProductCategoryPeer.doSelect(prodcatcrit));

            Criteria projcatcrit = new Criteria();
            projcatcrit.add(ProjectCategoryPeer.PROJECT_CAT_ID, 999, Criteria.GREATER_THAN);
            projcatcrit.addAscendingOrderByColumn(ProjectCategoryPeer.PROJECT_CAT_NAME);
            context.put("projectcats", ProjectCategoryPeer.doSelect(projcatcrit));
        }
        catch (Exception e)
        {
            // log something ?
        }
    }


    protected String formatDateTime(Date d)
    {
        SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.yyyy hh:mm:ss");
        return formatter.format(d);
    }

    protected String formatDate(Date d)
    {
        SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.yyyy");
        return formatter.format(d);
    }
    
}
