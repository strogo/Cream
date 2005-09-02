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
 * [Wed May 04 09:10:56 CEST 2005]
 *
 * You should not use this class directly.  It should not even be
 * extended all references should be to HouseholdCategory
 */
public abstract class BaseHouseholdCategory extends BaseObject
    implements org.apache.turbine.om.Retrievable
{
    /** The Peer class */
    private static final HouseholdCategoryPeer peer =
        new HouseholdCategoryPeer();

        
    /** The value for the householdCatId field */
    private int householdCatId;
      
    /** The value for the householdCatName field */
    private String householdCatName;
  
    
    /**
     * Get the HouseholdCatId
     *
     * @return int
     */
    public int getHouseholdCatId()
    {
        return householdCatId;
    }

                                              
    /**
     * Set the value of HouseholdCatId
     *
     * @param v new value
     */
    public void setHouseholdCatId(int v) throws TorqueException
    {
    
                  if (this.householdCatId != v)
              {
            this.householdCatId = v;
            setModified(true);
        }
    
          
                                  
                  // update associated Customer
        if (collCustomers != null)
        {
            for (int i = 0; i < collCustomers.size(); i++)
            {
                ((Customer) collCustomers.get(i))
                    .setHouseholdCatId(v);
            }
        }
                                }
  
    /**
     * Get the HouseholdCatName
     *
     * @return String
     */
    public String getHouseholdCatName()
    {
        return householdCatName;
    }

                        
    /**
     * Set the value of HouseholdCatName
     *
     * @param v new value
     */
    public void setHouseholdCatName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.householdCatName, v))
              {
            this.householdCatName = v;
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
        l.setHouseholdCategory((HouseholdCategory) this);
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
     * Otherwise if this HouseholdCategory has previously
     * been saved, it will retrieve related Customers from storage.
     * If this HouseholdCategory is new, it will return
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
                        criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId() );
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
                            criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
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
     * Otherwise if this HouseholdCategory has previously
     * been saved, it will retrieve related Customers from storage.
     * If this HouseholdCategory is new, it will return
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
                         criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
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
                              criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
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
     * Otherwise if this HouseholdCategory is new, it will return
     * an empty collection; or if this HouseholdCategory has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in HouseholdCategory.
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
                              criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
                              collCustomers = CustomerPeer.doSelectJoinCustomerCategory(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
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
     * Otherwise if this HouseholdCategory is new, it will return
     * an empty collection; or if this HouseholdCategory has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in HouseholdCategory.
     */
    protected List getCustomersJoinCountry(Criteria criteria)
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
                              criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
                              collCustomers = CustomerPeer.doSelectJoinCountry(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinCountry(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                  
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this HouseholdCategory is new, it will return
     * an empty collection; or if this HouseholdCategory has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in HouseholdCategory.
     */
    protected List getCustomersJoinRegion(Criteria criteria)
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
                              criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
                              collCustomers = CustomerPeer.doSelectJoinRegion(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinRegion(criteria);
            }
        }
        lastCustomersCriteria = criteria;

        return collCustomers;
                }
                  
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this HouseholdCategory is new, it will return
     * an empty collection; or if this HouseholdCategory has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in HouseholdCategory.
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
                              criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
                              collCustomers = CustomerPeer.doSelectJoinLanguage(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
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
     * Otherwise if this HouseholdCategory is new, it will return
     * an empty collection; or if this HouseholdCategory has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in HouseholdCategory.
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
                              criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
                              collCustomers = CustomerPeer.doSelectJoinEducationCategory(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
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
     * Otherwise if this HouseholdCategory is new, it will return
     * an empty collection; or if this HouseholdCategory has previously
     * been saved, it will retrieve related Customers from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in HouseholdCategory.
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
                              criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
                              collCustomers = CustomerPeer.doSelectJoinHouseholdCategory(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(CustomerPeer.HOUSEHOLD_CAT_ID, getHouseholdCatId());
                                    if (!lastCustomersCriteria.equals(criteria))
            {
                collCustomers = CustomerPeer.doSelectJoinHouseholdCategory(criteria);
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
              fieldNames.add("HouseholdCatId");
              fieldNames.add("HouseholdCatName");
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
          if (name.equals("HouseholdCatId"))
        {
                return new Integer(getHouseholdCatId());
            }
          if (name.equals("HouseholdCatName"))
        {
                return getHouseholdCatName();
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
          if (name.equals(HouseholdCategoryPeer.HOUSEHOLD_CAT_ID))
        {
                return new Integer(getHouseholdCatId());
            }
          if (name.equals(HouseholdCategoryPeer.HOUSEHOLD_CAT_NAME))
        {
                return getHouseholdCatName();
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
                return new Integer(getHouseholdCatId());
            }
              if (pos == 1)
        {
                return getHouseholdCatName();
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
          save(HouseholdCategoryPeer.getMapBuilder()
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
                    HouseholdCategoryPeer.doInsert((HouseholdCategory) this, con);
                    setNew(false);
                }
                else
                {
                    HouseholdCategoryPeer.doUpdate((HouseholdCategory) this, con);
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
     * @param key householdCatId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        throws TorqueException
    {
            setHouseholdCatId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) throws TorqueException
    {
            setHouseholdCatId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getHouseholdCatId());
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
      public HouseholdCategory copy() throws TorqueException
    {
        return copyInto(new HouseholdCategory());
    }
  
    protected HouseholdCategory copyInto(HouseholdCategory copyObj) throws TorqueException
    {
          copyObj.setHouseholdCatId(householdCatId);
          copyObj.setHouseholdCatName(householdCatName);
  
                            copyObj.setHouseholdCatId( 0);
                  
                                      
                            
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
    public HouseholdCategoryPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("HouseholdCategory:\n");
        str.append("HouseholdCatId = ")
               .append(getHouseholdCatId())
             .append("\n");
        str.append("HouseholdCatName = ")
               .append(getHouseholdCatName())
             .append("\n");
        return(str.toString());
    }
}