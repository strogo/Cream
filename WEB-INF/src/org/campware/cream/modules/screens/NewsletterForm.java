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

import org.campware.cream.om.Newsletter;
import org.campware.cream.om.NewsletterPeer;
import org.campware.cream.om.ProductPeer;
import org.campware.cream.om.ProjectPeer;
import org.campware.cream.om.LanguagePeer;
import org.campware.cream.om.CustomerCategoryPeer;
import org.campware.cream.om.CountryPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class NewsletterForm extends CreamForm
{
    protected void initScreen()
    {
        setModuleType(DOCUMENT);
        setModuleName("NEWSLETTER");
        setIdName(NewsletterPeer.NEWSLETTER_ID);
        setFormIdName("newsletterid");
    }

    protected boolean getEntry(Criteria criteria, Context context)
    {
        try
        {
            Newsletter entry = (Newsletter) NewsletterPeer.doSelect(criteria).get(0);
            context.put("entry", entry);
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
            Newsletter entry = new Newsletter();
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
            Criteria prodcrit = new Criteria();
            prodcrit.add(ProductPeer.PRODUCT_ID, 999, Criteria.GREATER_THAN);
            prodcrit.addAscendingOrderByColumn(ProductPeer.PRODUCT_DISPLAY);
            context.put("products", ProductPeer.doSelect(prodcrit));

            Criteria projcrit = new Criteria();
            projcrit.add(ProjectPeer.PROJECT_ID, 999, Criteria.GREATER_THAN);
            projcrit.addAscendingOrderByColumn(ProjectPeer.PROJECT_NAME);
            context.put("projects", ProjectPeer.doSelect(projcrit));

            Criteria langcrit = new Criteria();
            langcrit.add(LanguagePeer.LANGUAGE_ID, 999, Criteria.GREATER_THAN);
            langcrit.addAscendingOrderByColumn(LanguagePeer.LANGUAGE_NAME);
            context.put("languages", LanguagePeer.doSelect(langcrit));

            Criteria custcatcrit = new Criteria();
            custcatcrit.add(CustomerCategoryPeer.CUSTOMER_CAT_ID, 999, Criteria.GREATER_THAN);
            custcatcrit.addAscendingOrderByColumn(CustomerCategoryPeer.CUSTOMER_CAT_NAME);
            context.put("customercats", CustomerCategoryPeer.doSelect(custcatcrit));

            Criteria countrycrit = new Criteria();
            countrycrit.add(CountryPeer.COUNTRY_ID, 999, Criteria.GREATER_THAN);
            countrycrit.addAscendingOrderByColumn(CountryPeer.COUNTRY_NAME);
            context.put("countries", CountryPeer.doSelect(countrycrit));

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
