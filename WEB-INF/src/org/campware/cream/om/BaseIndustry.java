package org.campware.cream.om;


import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.turbine.om.Retrievable;
import org.apache.torque.TorqueException;
import org.apache.torque.om.BaseObject;
import org.apache.torque.om.ComboKey;
import org.apache.torque.om.DateKey;
import org.apache.torque.om.NumberKey;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.om.StringKey;
import org.apache.torque.om.Persistent;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;


/**
 * This class was autogenerated by Torque on:
 *
 * [Fri Jan 26 03:28:50 CET 2007]
 *
 * You should not use this class directly.  It should not even be
 * extended all references should be to Industry
 */
public abstract class BaseIndustry extends BaseObject
    implements org.apache.turbine.om.Retrievable
{
    /** The Peer class */
    private static final IndustryPeer peer =
        new IndustryPeer();

        
    /** The value for the industryId field */
    private int industryId;
      
    /** The value for the industryName field */
    private String industryName;
  
    
    /**
     * Get the IndustryId
     *
     * @return int
     */
    public int getIndustryId()
    {
        return industryId;
    }

                                              
    /**
     * Set the value of IndustryId
     *
     * @param v new value
     */
    public void setIndustryId(int v) throws TorqueException
    {
    
                  if (this.industryId != v)
              {
            this.industryId = v;
            setModified(true);
        }
    
          
                                  
                  // update associated Customer
        if (collCustomers != null)
        {
            for (int i = 0; i < collCustomers.size(); i++)
            {
                ((Customer) collCustomers.get(i))
                    .setIndustryId(v);
            }
        }
                                }
  
    /**
     * Get the IndustryName
     *
     * @return String
     */
    public String getIndustryName()
    {
        return industryName;
    }

                        
    /**
     * Set the value of IndustryName
     *
     * @param v new value
     */
    public void setIndustryName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.industryName, v))
              {
            this.industryName = v;
            setModified(true);
        }
    
          
              }
  
         
                                
            
          /**
     * Collection to store aggregation of collCustomers
     */
    protected List collCustomers;

    /**
     * Temporary storage of collCustomers to save a possible db hit in
     * the event objects are add to the collection, but the
     * complete collection is never requested.
     */
    protected void initCustomers()
    {
        if (collCustomers == null)
        {
            collCustomers = new ArrayList();
        }
    }

    /**
     * Method called to associate a Customer object to this object
     * through the Customer foreign key attribute
     *
     * @param l Customer
     * @throws TorqueException
     */
    public void addCustomer(Customer l) throws TorqueException
    {
        getCustomers().add(l);
        l.setIndustry((Industry) this);
    }

    /**
     * The criteria used to select the current contents of collCustomers
     */
    private Criteria lastCustomersCriteria = null;
      
    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getCustomers(new Criteria())
     *
     * @throws TorqueException
     */
    public List getCustomers() throws TorqueException
    {
              if (collCustomers == null)
        {
            collCustomers = getCustomers(new Criteria(10));
        }
        return collCustomers;
          }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     * If this Industry is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     *
     * @throws TorqueException
     */
    public List getCustomers(Criteria criteria) throws TorqueException
    {
              if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                        criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId() );
                        collCustomers = CustomerPeer.doSelect(criteria);
            }
        }
        else
        {
            // criteria has no effect for a new object
            if (!isNew())
            {
                // the following code is to determine if a new query is
                // called for.  If the criteria is the same as the last
                // one, just return the collection.
                            criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                            if (!lastCustomersCriteria.equals(criteria))
                {
                    collCustomers = CustomerPeer.doSelect(criteria);
                }
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
          }

    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getCustomers(new Criteria(),Connection)
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getCustomers(Connection con) throws TorqueException
    {
              if (collCustomers == null)
        {
            collCustomers = getCustomers(new Criteria(10), con);
        }
        return collCustomers;
          }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     * If this Industry is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getCustomers(Criteria criteria, Connection con)
            throws TorqueException
    {
              if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                         criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                         collCustomers = CustomerPeer.doSelect(criteria, con);
             }
         }
         else
         {
             // criteria has no effect for a new object
             if (!isNew())
             {
                 // the following code is to determine if a new query is
                 // called for.  If the criteria is the same as the last
                 // one, just return the collection.
                              criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                             if (!lastCustomersCriteria.equals(criteria))
                 {
                     collCustomers = CustomerPeer.doSelect(criteria, con);
                 }
             }
         }
         lastCustomersCriteria = criteria;

         return collCustomers;
           }

                                                                                          
              
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry is new, it will return
     * an empty collection; or if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Industry.
     */
    protected List getCustomersJoinCustomerCategory(Criteria criteria)
        throws TorqueException
    {
                    if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                              criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                              collCustomers = CustomerPeer.doSelectJoinCustomerCategory(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinCustomerCategory(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                  
                    
                              
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry is new, it will return
     * an empty collection; or if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Industry.
     */
    protected List getCustomersJoinIndustry(Criteria criteria)
        throws TorqueException
    {
                    if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                              criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                              collCustomers = CustomerPeer.doSelectJoinIndustry(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinIndustry(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                  
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry is new, it will return
     * an empty collection; or if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Industry.
     */
    protected List getCustomersJoinLeadSource(Criteria criteria)
        throws TorqueException
    {
                    if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                              criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                              collCustomers = CustomerPeer.doSelectJoinLeadSource(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinLeadSource(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                  
                    
                    
                                            
                                                                          
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry is new, it will return
     * an empty collection; or if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Industry.
     */
    protected List getCustomersJoinCountryRelatedByCountryId(Criteria criteria)
        throws TorqueException
    {
                    if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                              criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                              collCustomers = CustomerPeer.doSelectJoinCountryRelatedByCountryId(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinCountryRelatedByCountryId(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                  
                    
                    
                                            
                                                                          
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry is new, it will return
     * an empty collection; or if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Industry.
     */
    protected List getCustomersJoinRegionRelatedByRegionId(Criteria criteria)
        throws TorqueException
    {
                    if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                              criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                              collCustomers = CustomerPeer.doSelectJoinRegionRelatedByRegionId(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinRegionRelatedByRegionId(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                  
                    
                    
                                            
                                                                          
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry is new, it will return
     * an empty collection; or if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Industry.
     */
    protected List getCustomersJoinCountryRelatedByShiptoCountryId(Criteria criteria)
        throws TorqueException
    {
                    if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                              criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                              collCustomers = CustomerPeer.doSelectJoinCountryRelatedByShiptoCountryId(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinCountryRelatedByShiptoCountryId(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                  
                    
                    
                                            
                                                                          
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry is new, it will return
     * an empty collection; or if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Industry.
     */
    protected List getCustomersJoinRegionRelatedByShiptoRegionId(Criteria criteria)
        throws TorqueException
    {
                    if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                              criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                              collCustomers = CustomerPeer.doSelectJoinRegionRelatedByShiptoRegionId(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinRegionRelatedByShiptoRegionId(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                  
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry is new, it will return
     * an empty collection; or if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Industry.
     */
    protected List getCustomersJoinLanguage(Criteria criteria)
        throws TorqueException
    {
                    if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                              criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                              collCustomers = CustomerPeer.doSelectJoinLanguage(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinLanguage(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                  
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry is new, it will return
     * an empty collection; or if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Industry.
     */
    protected List getCustomersJoinEducationCategory(Criteria criteria)
        throws TorqueException
    {
                    if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                              criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                              collCustomers = CustomerPeer.doSelectJoinEducationCategory(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinEducationCategory(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                  
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry is new, it will return
     * an empty collection; or if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Industry.
     */
    protected List getCustomersJoinHouseholdCategory(Criteria criteria)
        throws TorqueException
    {
                    if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                              criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                              collCustomers = CustomerPeer.doSelectJoinHouseholdCategory(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinHouseholdCategory(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                  
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry is new, it will return
     * an empty collection; or if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Industry.
     */
    protected List getCustomersJoinAgeCategory(Criteria criteria)
        throws TorqueException
    {
                    if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                              criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                              collCustomers = CustomerPeer.doSelectJoinAgeCategory(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinAgeCategory(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                  
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry is new, it will return
     * an empty collection; or if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Industry.
     */
    protected List getCustomersJoinRevenueCategory(Criteria criteria)
        throws TorqueException
    {
                    if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                              criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                              collCustomers = CustomerPeer.doSelectJoinRevenueCategory(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinRevenueCategory(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                  
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Industry is new, it will return
     * an empty collection; or if this Industry has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Industry.
     */
    protected List getCustomersJoinEmployeeNoCategory(Criteria criteria)
        throws TorqueException
    {
                    if (collCustomers == null)
        {
            if (isNew())
            {
               collCustomers = new ArrayList();
            }
            else
            {
                              criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                              collCustomers = CustomerPeer.doSelectJoinEmployeeNoCategory(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.INDUSTRY_ID, getIndustryId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinEmployeeNoCategory(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                            


          
    private static List fieldNames = null;

    /**
     * Generate a list of field names.
     *
     * @return a list of field names
     */
    public static synchronized List getFieldNames()
    {
        if (fieldNames == null)
        {
            fieldNames = new ArrayList();
              fieldNames.add("IndustryId");
              fieldNames.add("IndustryName");
              fieldNames = Collections.unmodifiableList(fieldNames);
        }
        return fieldNames;
    }

    /**
     * Retrieves a field from the object by name passed in as a String.
     *
     * @param name field name
     * @return value
     */
    public Object getByName(String name)
    {
          if (name.equals("IndustryId"))
        {
                return new Integer(getIndustryId());
            }
          if (name.equals("IndustryName"))
        {
                return getIndustryName();
            }
          return null;
    }
    
    /**
     * Retrieves a field from the object by name passed in
     * as a String.  The String must be one of the static
     * Strings defined in this Class' Peer.
     *
     * @param name peer name
     * @return value
     */
    public Object getByPeerName(String name)
    {
          if (name.equals(IndustryPeer.INDUSTRY_ID))
        {
                return new Integer(getIndustryId());
            }
          if (name.equals(IndustryPeer.INDUSTRY_NAME))
        {
                return getIndustryName();
            }
          return null;
    }

    /**
     * Retrieves a field from the object by Position as specified
     * in the xml schema.  Zero-based.
     *
     * @param pos position in xml schema
     * @return value
     */
    public Object getByPosition(int pos)
    {
            if (pos == 0)
        {
                return new Integer(getIndustryId());
            }
              if (pos == 1)
        {
                return getIndustryName();
            }
              return null;
    }
     
    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     *
     * @throws Exception
     */
    public void save() throws Exception
    {
          save(IndustryPeer.getMapBuilder()
                .getDatabaseMap().getName());
      }

    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
       * Note: this code is here because the method body is
     * auto-generated conditionally and therefore needs to be
     * in this file instead of in the super class, BaseObject.
       *
     * @param dbName
     * @throws TorqueException
     */
    public void save(String dbName) throws TorqueException
    {
        Connection con = null;
          try
        {
            con = Transaction.begin(dbName);
            save(con);
            Transaction.commit(con);
        }
        catch(TorqueException e)
        {
            Transaction.safeRollback(con);
            throw e;
        }
      }

      /** flag to prevent endless save loop, if this object is referenced
        by another object which falls in this transaction. */
    private boolean alreadyInSave = false;
      /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.  This method
     * is meant to be used as part of a transaction, otherwise use
     * the save() method and the connection details will be handled
     * internally
     *
     * @param con
     * @throws TorqueException
     */
    public void save(Connection con) throws TorqueException
    {
          if (!alreadyInSave)
        {
            alreadyInSave = true;


  
            // If this object has been modified, then save it to the database.
            if (isModified())
            {
                if (isNew())
                {
                    IndustryPeer.doInsert((Industry) this, con);
                    setNew(false);
                }
                else
                {
                    IndustryPeer.doUpdate((Industry) this, con);
                }
            }

                                      
                
                    if (collCustomers != null)
            {
                for (int i = 0; i < collCustomers.size(); i++)
                {
                    ((Customer) collCustomers.get(i)).save(con);
                }
            }
                                  alreadyInSave = false;
        }
      }

                        
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param key industryId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        throws TorqueException
    {
            setIndustryId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) throws TorqueException
    {
            setIndustryId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getIndustryId());
      }
 
    /**
     * get an id that differentiates this object from others
     * of its class.
     */
    public String getQueryKey()
    {
        if (getPrimaryKey() == null)
        {
            return "";
        }
        else
        {
            return getPrimaryKey().toString();
        }
    }

    /**
     * set an id that differentiates this object from others
     * of its class.
     */
    public void setQueryKey(String key)
        throws TorqueException
    {
        setPrimaryKey(key);
    }

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
       * It then fills all the association collections and sets the
     * related objects to isNew=true.
       */
      public Industry copy() throws TorqueException
    {
        return copyInto(new Industry());
    }
  
    protected Industry copyInto(Industry copyObj) throws TorqueException
    {
          copyObj.setIndustryId(industryId);
          copyObj.setIndustryName(industryName);
  
                            copyObj.setIndustryId( 0);
                  
                                      
                            
        List v = getCustomers();
        for (int i = 0; i < v.size(); i++)
        {
            Customer obj = (Customer) v.get(i);
            copyObj.addCustomer(obj.copy());
        }
                            return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public IndustryPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("Industry:\n");
        str.append("IndustryId = ")
               .append(getIndustryId())
             .append("\n");
        str.append("IndustryName = ")
               .append(getIndustryName())
             .append("\n");
        return(str.toString());
    }
}
