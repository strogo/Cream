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
 * extended all references should be to Uom
 */
public abstract class BaseUom extends BaseObject
    implements org.apache.turbine.om.Retrievable
{
    /** The Peer class */
    private static final UomPeer peer =
        new UomPeer();

        
    /** The value for the uomId field */
    private int uomId;
      
    /** The value for the uomName field */
    private String uomName;
      
    /** The value for the uomCode field */
    private String uomCode;
  
    
    /**
     * Get the UomId
     *
     * @return int
     */
    public int getUomId()
    {
        return uomId;
    }

                                              
    /**
     * Set the value of UomId
     *
     * @param v new value
     */
    public void setUomId(int v) throws TorqueException
    {
    
                  if (this.uomId != v)
              {
            this.uomId = v;
            setModified(true);
        }
    
          
                                  
                  // update associated Product
        if (collProducts != null)
        {
            for (int i = 0; i < collProducts.size(); i++)
            {
                ((Product) collProducts.get(i))
                    .setUomId(v);
            }
        }
                                }
  
    /**
     * Get the UomName
     *
     * @return String
     */
    public String getUomName()
    {
        return uomName;
    }

                        
    /**
     * Set the value of UomName
     *
     * @param v new value
     */
    public void setUomName(String v) 
    {
    
                  if (!ObjectUtils.equals(this.uomName, v))
              {
            this.uomName = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the UomCode
     *
     * @return String
     */
    public String getUomCode()
    {
        return uomCode;
    }

                        
    /**
     * Set the value of UomCode
     *
     * @param v new value
     */
    public void setUomCode(String v) 
    {
    
                  if (!ObjectUtils.equals(this.uomCode, v))
              {
            this.uomCode = v;
            setModified(true);
        }
    
          
              }
  
         
                                
            
          /**
     * Collection to store aggregation of collProducts
     */
    protected List collProducts;

    /**
     * Temporary storage of collProducts to save a possible db hit in
     * the event objects are add to the collection, but the
     * complete collection is never requested.
     */
    protected void initProducts()
    {
        if (collProducts == null)
        {
            collProducts = new ArrayList();
        }
    }

    /**
     * Method called to associate a Product object to this object
     * through the Product foreign key attribute
     *
     * @param l Product
     * @throws TorqueException
     */
    public void addProduct(Product l) throws TorqueException
    {
        getProducts().add(l);
        l.setUom((Uom) this);
    }

    /**
     * The criteria used to select the current contents of collProducts
     */
    private Criteria lastProductsCriteria = null;
      
    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getProducts(new Criteria())
     *
     * @throws TorqueException
     */
    public List getProducts() throws TorqueException
    {
              if (collProducts == null)
        {
            collProducts = getProducts(new Criteria(10));
        }
        return collProducts;
          }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Uom has previously
     * been saved, it will retrieve related Products from storage.
     * If this Uom is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     *
     * @throws TorqueException
     */
    public List getProducts(Criteria criteria) throws TorqueException
    {
              if (collProducts == null)
        {
            if (isNew())
            {
               collProducts = new ArrayList();
            }
            else
            {
                        criteria.add(ProductPeer.UOM_ID, getUomId() );
                        collProducts = ProductPeer.doSelect(criteria);
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
                            criteria.add(ProductPeer.UOM_ID, getUomId());
                            if (!lastProductsCriteria.equals(criteria))
                {
                    collProducts = ProductPeer.doSelect(criteria);
                }
            }
        }
        lastProductsCriteria = criteria;

        return collProducts;
          }

    /**
     * If this collection has already been initialized, returns
     * the collection. Otherwise returns the results of
     * getProducts(new Criteria(),Connection)
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getProducts(Connection con) throws TorqueException
    {
              if (collProducts == null)
        {
            collProducts = getProducts(new Criteria(10), con);
        }
        return collProducts;
          }

    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Uom has previously
     * been saved, it will retrieve related Products from storage.
     * If this Uom is new, it will return
     * an empty collection or the current collection, the criteria
     * is ignored on a new object.
     * This method takes in the Connection also as input so that
     * referenced objects can also be obtained using a Connection
     * that is taken as input
     */
    public List getProducts(Criteria criteria, Connection con)
            throws TorqueException
    {
              if (collProducts == null)
        {
            if (isNew())
            {
               collProducts = new ArrayList();
            }
            else
            {
                         criteria.add(ProductPeer.UOM_ID, getUomId());
                         collProducts = ProductPeer.doSelect(criteria, con);
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
                              criteria.add(ProductPeer.UOM_ID, getUomId());
                             if (!lastProductsCriteria.equals(criteria))
                 {
                     collProducts = ProductPeer.doSelect(criteria, con);
                 }
             }
         }
         lastProductsCriteria = criteria;

         return collProducts;
           }

                              
              
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Uom is new, it will return
     * an empty collection; or if this Uom has previously
     * been saved, it will retrieve related Products from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Uom.
     */
    protected List getProductsJoinProductCategory(Criteria criteria)
        throws TorqueException
    {
                    if (collProducts == null)
        {
            if (isNew())
            {
               collProducts = new ArrayList();
            }
            else
            {
                              criteria.add(ProductPeer.UOM_ID, getUomId());
                              collProducts = ProductPeer.doSelectJoinProductCategory(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(ProductPeer.UOM_ID, getUomId());
                                    if (!lastProductsCriteria.equals(criteria))
            {
                collProducts = ProductPeer.doSelectJoinProductCategory(criteria);
            }
        }
        lastProductsCriteria = criteria;

        return collProducts;
                }
                  
                    
                              
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Uom is new, it will return
     * an empty collection; or if this Uom has previously
     * been saved, it will retrieve related Products from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Uom.
     */
    protected List getProductsJoinUom(Criteria criteria)
        throws TorqueException
    {
                    if (collProducts == null)
        {
            if (isNew())
            {
               collProducts = new ArrayList();
            }
            else
            {
                              criteria.add(ProductPeer.UOM_ID, getUomId());
                              collProducts = ProductPeer.doSelectJoinUom(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(ProductPeer.UOM_ID, getUomId());
                                    if (!lastProductsCriteria.equals(criteria))
            {
                collProducts = ProductPeer.doSelectJoinUom(criteria);
            }
        }
        lastProductsCriteria = criteria;

        return collProducts;
                }
                  
                    
                    
                                
                                                              
                                        
                    
                    
          
    /**
     * If this collection has already been initialized with
     * an identical criteria, it returns the collection.
     * Otherwise if this Uom is new, it will return
     * an empty collection; or if this Uom has previously
     * been saved, it will retrieve related Products from storage.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in Uom.
     */
    protected List getProductsJoinVendor(Criteria criteria)
        throws TorqueException
    {
                    if (collProducts == null)
        {
            if (isNew())
            {
               collProducts = new ArrayList();
            }
            else
            {
                              criteria.add(ProductPeer.UOM_ID, getUomId());
                              collProducts = ProductPeer.doSelectJoinVendor(criteria);
            }
        }
        else
        {
            // the following code is to determine if a new query is
            // called for.  If the criteria is the same as the last
            // one, just return the collection.
            
                        criteria.add(ProductPeer.UOM_ID, getUomId());
                                    if (!lastProductsCriteria.equals(criteria))
            {
                collProducts = ProductPeer.doSelectJoinVendor(criteria);
            }
        }
        lastProductsCriteria = criteria;

        return collProducts;
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
              fieldNames.add("UomId");
              fieldNames.add("UomName");
              fieldNames.add("UomCode");
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
          if (name.equals("UomId"))
        {
                return new Integer(getUomId());
            }
          if (name.equals("UomName"))
        {
                return getUomName();
            }
          if (name.equals("UomCode"))
        {
                return getUomCode();
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
          if (name.equals(UomPeer.UOM_ID))
        {
                return new Integer(getUomId());
            }
          if (name.equals(UomPeer.UOM_NAME))
        {
                return getUomName();
            }
          if (name.equals(UomPeer.UOM_CODE))
        {
                return getUomCode();
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
                return new Integer(getUomId());
            }
              if (pos == 1)
        {
                return getUomName();
            }
              if (pos == 2)
        {
                return getUomCode();
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
          save(UomPeer.getMapBuilder()
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
                    UomPeer.doInsert((Uom) this, con);
                    setNew(false);
                }
                else
                {
                    UomPeer.doUpdate((Uom) this, con);
                }
            }

                                      
                
                    if (collProducts != null)
            {
                for (int i = 0; i < collProducts.size(); i++)
                {
                    ((Product) collProducts.get(i)).save(con);
                }
            }
                                  alreadyInSave = false;
        }
      }

                        
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param key uomId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        throws TorqueException
    {
            setUomId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) throws TorqueException
    {
            setUomId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getUomId());
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
      public Uom copy() throws TorqueException
    {
        return copyInto(new Uom());
    }
  
    protected Uom copyInto(Uom copyObj) throws TorqueException
    {
          copyObj.setUomId(uomId);
          copyObj.setUomName(uomName);
          copyObj.setUomCode(uomCode);
  
                            copyObj.setUomId( 0);
                        
                                      
                            
        List v = getProducts();
        for (int i = 0; i < v.size(); i++)
        {
            Product obj = (Product) v.get(i);
            copyObj.addProduct(obj.copy());
        }
                            return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public UomPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("Uom:\n");
        str.append("UomId = ")
               .append(getUomId())
             .append("\n");
        str.append("UomName = ")
               .append(getUomName())
             .append("\n");
        str.append("UomCode = ")
               .append(getUomCode())
             .append("\n");
        return(str.toString());
    }
}
