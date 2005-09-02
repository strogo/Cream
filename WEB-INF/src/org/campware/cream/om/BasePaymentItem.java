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
 * extended all references should be to PaymentItem
 */
public abstract class BasePaymentItem extends BaseObject
    implements org.apache.turbine.om.Retrievable
{
    /** The Peer class */
    private static final PaymentItemPeer peer =
        new PaymentItemPeer();

        
    /** The value for the paymentItemId field */
    private int paymentItemId;
                                          
    /** The value for the paymentId field */
    private int paymentId = 1000;
                                          
    /** The value for the sorderId field */
    private int sorderId = 1000;
                                          
    /** The value for the customerId field */
    private int customerId = 1000;
                                          
    /** The value for the projectId field */
    private int projectId = 1000;
                                          
    /** The value for the productId field */
    private int productId = 1000;
      
    /** The value for the description field */
    private String description;
                                  
    /** The value for the unitPrice field */
    private BigDecimal unitPrice= new BigDecimal(0);
                                          
    /** The value for the quantity field */
    private int quantity = 1;
                                          
    /** The value for the currencyId field */
    private int currencyId = 1000;
                                  
    /** The value for the itemCurrTotal field */
    private BigDecimal itemCurrTotal= new BigDecimal(0);
                                  
    /** The value for the itemTotal field */
    private BigDecimal itemTotal= new BigDecimal(0);
  
    
    /**
     * Get the PaymentItemId
     *
     * @return int
     */
    public int getPaymentItemId()
    {
        return paymentItemId;
    }

                        
    /**
     * Set the value of PaymentItemId
     *
     * @param v new value
     */
    public void setPaymentItemId(int v) 
    {
    
                  if (this.paymentItemId != v)
              {
            this.paymentItemId = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the PaymentId
     *
     * @return int
     */
    public int getPaymentId()
    {
        return paymentId;
    }

                              
    /**
     * Set the value of PaymentId
     *
     * @param v new value
     */
    public void setPaymentId(int v) throws TorqueException
    {
    
                  if (this.paymentId != v)
              {
            this.paymentId = v;
            setModified(true);
        }
    
                          
                if (aPayment != null && !(aPayment.getPaymentId() == v))
                {
            aPayment = null;
        }
      
              }
  
    /**
     * Get the SorderId
     *
     * @return int
     */
    public int getSorderId()
    {
        return sorderId;
    }

                              
    /**
     * Set the value of SorderId
     *
     * @param v new value
     */
    public void setSorderId(int v) throws TorqueException
    {
    
                  if (this.sorderId != v)
              {
            this.sorderId = v;
            setModified(true);
        }
    
                          
                if (aSorder != null && !(aSorder.getSorderId() == v))
                {
            aSorder = null;
        }
      
              }
  
    /**
     * Get the CustomerId
     *
     * @return int
     */
    public int getCustomerId()
    {
        return customerId;
    }

                              
    /**
     * Set the value of CustomerId
     *
     * @param v new value
     */
    public void setCustomerId(int v) throws TorqueException
    {
    
                  if (this.customerId != v)
              {
            this.customerId = v;
            setModified(true);
        }
    
                          
                if (aCustomer != null && !(aCustomer.getCustomerId() == v))
                {
            aCustomer = null;
        }
      
              }
  
    /**
     * Get the ProjectId
     *
     * @return int
     */
    public int getProjectId()
    {
        return projectId;
    }

                              
    /**
     * Set the value of ProjectId
     *
     * @param v new value
     */
    public void setProjectId(int v) throws TorqueException
    {
    
                  if (this.projectId != v)
              {
            this.projectId = v;
            setModified(true);
        }
    
                          
                if (aProject != null && !(aProject.getProjectId() == v))
                {
            aProject = null;
        }
      
              }
  
    /**
     * Get the ProductId
     *
     * @return int
     */
    public int getProductId()
    {
        return productId;
    }

                              
    /**
     * Set the value of ProductId
     *
     * @param v new value
     */
    public void setProductId(int v) throws TorqueException
    {
    
                  if (this.productId != v)
              {
            this.productId = v;
            setModified(true);
        }
    
                          
                if (aProduct != null && !(aProduct.getProductId() == v))
                {
            aProduct = null;
        }
      
              }
  
    /**
     * Get the Description
     *
     * @return String
     */
    public String getDescription()
    {
        return description;
    }

                        
    /**
     * Set the value of Description
     *
     * @param v new value
     */
    public void setDescription(String v) 
    {
    
                  if (!ObjectUtils.equals(this.description, v))
              {
            this.description = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the UnitPrice
     *
     * @return BigDecimal
     */
    public BigDecimal getUnitPrice()
    {
        return unitPrice;
    }

                        
    /**
     * Set the value of UnitPrice
     *
     * @param v new value
     */
    public void setUnitPrice(BigDecimal v) 
    {
    
                  if (!ObjectUtils.equals(this.unitPrice, v))
              {
            this.unitPrice = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the Quantity
     *
     * @return int
     */
    public int getQuantity()
    {
        return quantity;
    }

                        
    /**
     * Set the value of Quantity
     *
     * @param v new value
     */
    public void setQuantity(int v) 
    {
    
                  if (this.quantity != v)
              {
            this.quantity = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the CurrencyId
     *
     * @return int
     */
    public int getCurrencyId()
    {
        return currencyId;
    }

                              
    /**
     * Set the value of CurrencyId
     *
     * @param v new value
     */
    public void setCurrencyId(int v) throws TorqueException
    {
    
                  if (this.currencyId != v)
              {
            this.currencyId = v;
            setModified(true);
        }
    
                          
                if (aCurrency != null && !(aCurrency.getCurrencyId() == v))
                {
            aCurrency = null;
        }
      
              }
  
    /**
     * Get the ItemCurrTotal
     *
     * @return BigDecimal
     */
    public BigDecimal getItemCurrTotal()
    {
        return itemCurrTotal;
    }

                        
    /**
     * Set the value of ItemCurrTotal
     *
     * @param v new value
     */
    public void setItemCurrTotal(BigDecimal v) 
    {
    
                  if (!ObjectUtils.equals(this.itemCurrTotal, v))
              {
            this.itemCurrTotal = v;
            setModified(true);
        }
    
          
              }
  
    /**
     * Get the ItemTotal
     *
     * @return BigDecimal
     */
    public BigDecimal getItemTotal()
    {
        return itemTotal;
    }

                        
    /**
     * Set the value of ItemTotal
     *
     * @param v new value
     */
    public void setItemTotal(BigDecimal v) 
    {
    
                  if (!ObjectUtils.equals(this.itemTotal, v))
              {
            this.itemTotal = v;
            setModified(true);
        }
    
          
              }
  
      
    
                  
    
        private Payment aPayment;

    /**
     * Declares an association between this object and a Payment object
     *
     * @param v Payment
     * @throws TorqueException
     */
    public void setPayment(Payment v) throws TorqueException
    {
            if (v == null)
        {
                          setPaymentId( 1000);
              }
        else
        {
            setPaymentId(v.getPaymentId());
        }
            aPayment = v;
    }

                                            
    /**
     * Get the associated Payment object
     *
     * @return the associated Payment object
     * @throws TorqueException
     */
    public Payment getPayment() throws TorqueException
    {
        if (aPayment == null && (this.paymentId != 0))
        {
                          aPayment = PaymentPeer.retrieveByPK(SimpleKey.keyFor(this.paymentId));
              
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Payment obj = PaymentPeer.retrieveByPK(this.paymentId);
               obj.addPaymentItems(this);
            */
        }
        return aPayment;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setPaymentKey(ObjectKey key) throws TorqueException
    {
      
                        setPaymentId(((NumberKey) key).intValue());
                  }
    
    
                  
    
        private Sorder aSorder;

    /**
     * Declares an association between this object and a Sorder object
     *
     * @param v Sorder
     * @throws TorqueException
     */
    public void setSorder(Sorder v) throws TorqueException
    {
            if (v == null)
        {
                          setSorderId( 1000);
              }
        else
        {
            setSorderId(v.getSorderId());
        }
            aSorder = v;
    }

                                            
    /**
     * Get the associated Sorder object
     *
     * @return the associated Sorder object
     * @throws TorqueException
     */
    public Sorder getSorder() throws TorqueException
    {
        if (aSorder == null && (this.sorderId != 0))
        {
                          aSorder = SorderPeer.retrieveByPK(SimpleKey.keyFor(this.sorderId));
              
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Sorder obj = SorderPeer.retrieveByPK(this.sorderId);
               obj.addPaymentItems(this);
            */
        }
        return aSorder;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setSorderKey(ObjectKey key) throws TorqueException
    {
      
                        setSorderId(((NumberKey) key).intValue());
                  }
    
    
                  
    
        private Product aProduct;

    /**
     * Declares an association between this object and a Product object
     *
     * @param v Product
     * @throws TorqueException
     */
    public void setProduct(Product v) throws TorqueException
    {
            if (v == null)
        {
                          setProductId( 1000);
              }
        else
        {
            setProductId(v.getProductId());
        }
            aProduct = v;
    }

                                            
    /**
     * Get the associated Product object
     *
     * @return the associated Product object
     * @throws TorqueException
     */
    public Product getProduct() throws TorqueException
    {
        if (aProduct == null && (this.productId != 0))
        {
                          aProduct = ProductPeer.retrieveByPK(SimpleKey.keyFor(this.productId));
              
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Product obj = ProductPeer.retrieveByPK(this.productId);
               obj.addPaymentItems(this);
            */
        }
        return aProduct;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setProductKey(ObjectKey key) throws TorqueException
    {
      
                        setProductId(((NumberKey) key).intValue());
                  }
    
    
                  
    
        private Currency aCurrency;

    /**
     * Declares an association between this object and a Currency object
     *
     * @param v Currency
     * @throws TorqueException
     */
    public void setCurrency(Currency v) throws TorqueException
    {
            if (v == null)
        {
                          setCurrencyId( 1000);
              }
        else
        {
            setCurrencyId(v.getCurrencyId());
        }
            aCurrency = v;
    }

                                            
    /**
     * Get the associated Currency object
     *
     * @return the associated Currency object
     * @throws TorqueException
     */
    public Currency getCurrency() throws TorqueException
    {
        if (aCurrency == null && (this.currencyId != 0))
        {
                          aCurrency = CurrencyPeer.retrieveByPK(SimpleKey.keyFor(this.currencyId));
              
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Currency obj = CurrencyPeer.retrieveByPK(this.currencyId);
               obj.addPaymentItems(this);
            */
        }
        return aCurrency;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setCurrencyKey(ObjectKey key) throws TorqueException
    {
      
                        setCurrencyId(((NumberKey) key).intValue());
                  }
    
    
                  
    
        private Customer aCustomer;

    /**
     * Declares an association between this object and a Customer object
     *
     * @param v Customer
     * @throws TorqueException
     */
    public void setCustomer(Customer v) throws TorqueException
    {
            if (v == null)
        {
                          setCustomerId( 1000);
              }
        else
        {
            setCustomerId(v.getCustomerId());
        }
            aCustomer = v;
    }

                                            
    /**
     * Get the associated Customer object
     *
     * @return the associated Customer object
     * @throws TorqueException
     */
    public Customer getCustomer() throws TorqueException
    {
        if (aCustomer == null && (this.customerId != 0))
        {
                          aCustomer = CustomerPeer.retrieveByPK(SimpleKey.keyFor(this.customerId));
              
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Customer obj = CustomerPeer.retrieveByPK(this.customerId);
               obj.addPaymentItems(this);
            */
        }
        return aCustomer;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setCustomerKey(ObjectKey key) throws TorqueException
    {
      
                        setCustomerId(((NumberKey) key).intValue());
                  }
    
    
                  
    
        private Project aProject;

    /**
     * Declares an association between this object and a Project object
     *
     * @param v Project
     * @throws TorqueException
     */
    public void setProject(Project v) throws TorqueException
    {
            if (v == null)
        {
                          setProjectId( 1000);
              }
        else
        {
            setProjectId(v.getProjectId());
        }
            aProject = v;
    }

                                            
    /**
     * Get the associated Project object
     *
     * @return the associated Project object
     * @throws TorqueException
     */
    public Project getProject() throws TorqueException
    {
        if (aProject == null && (this.projectId != 0))
        {
                          aProject = ProjectPeer.retrieveByPK(SimpleKey.keyFor(this.projectId));
              
            /* The following can be used instead of the line above to
               guarantee the related object contains a reference
               to this object, but this level of coupling
               may be undesirable in many circumstances.
               As it can lead to a db query with many results that may
               never be used.
               Project obj = ProjectPeer.retrieveByPK(this.projectId);
               obj.addPaymentItems(this);
            */
        }
        return aProject;
    }

    /**
     * Provides convenient way to set a relationship based on a
     * ObjectKey, for example
     * <code>bar.setFooKey(foo.getPrimaryKey())</code>
     *
         */
    public void setProjectKey(ObjectKey key) throws TorqueException
    {
      
                        setProjectId(((NumberKey) key).intValue());
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
              fieldNames.add("PaymentItemId");
              fieldNames.add("PaymentId");
              fieldNames.add("SorderId");
              fieldNames.add("CustomerId");
              fieldNames.add("ProjectId");
              fieldNames.add("ProductId");
              fieldNames.add("Description");
              fieldNames.add("UnitPrice");
              fieldNames.add("Quantity");
              fieldNames.add("CurrencyId");
              fieldNames.add("ItemCurrTotal");
              fieldNames.add("ItemTotal");
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
          if (name.equals("PaymentItemId"))
        {
                return new Integer(getPaymentItemId());
            }
          if (name.equals("PaymentId"))
        {
                return new Integer(getPaymentId());
            }
          if (name.equals("SorderId"))
        {
                return new Integer(getSorderId());
            }
          if (name.equals("CustomerId"))
        {
                return new Integer(getCustomerId());
            }
          if (name.equals("ProjectId"))
        {
                return new Integer(getProjectId());
            }
          if (name.equals("ProductId"))
        {
                return new Integer(getProductId());
            }
          if (name.equals("Description"))
        {
                return getDescription();
            }
          if (name.equals("UnitPrice"))
        {
                return getUnitPrice();
            }
          if (name.equals("Quantity"))
        {
                return new Integer(getQuantity());
            }
          if (name.equals("CurrencyId"))
        {
                return new Integer(getCurrencyId());
            }
          if (name.equals("ItemCurrTotal"))
        {
                return getItemCurrTotal();
            }
          if (name.equals("ItemTotal"))
        {
                return getItemTotal();
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
          if (name.equals(PaymentItemPeer.PAYMENT_ITEM_ID))
        {
                return new Integer(getPaymentItemId());
            }
          if (name.equals(PaymentItemPeer.PAYMENT_ID))
        {
                return new Integer(getPaymentId());
            }
          if (name.equals(PaymentItemPeer.SORDER_ID))
        {
                return new Integer(getSorderId());
            }
          if (name.equals(PaymentItemPeer.CUSTOMER_ID))
        {
                return new Integer(getCustomerId());
            }
          if (name.equals(PaymentItemPeer.PROJECT_ID))
        {
                return new Integer(getProjectId());
            }
          if (name.equals(PaymentItemPeer.PRODUCT_ID))
        {
                return new Integer(getProductId());
            }
          if (name.equals(PaymentItemPeer.DESCRIPTION))
        {
                return getDescription();
            }
          if (name.equals(PaymentItemPeer.UNIT_PRICE))
        {
                return getUnitPrice();
            }
          if (name.equals(PaymentItemPeer.QUANTITY))
        {
                return new Integer(getQuantity());
            }
          if (name.equals(PaymentItemPeer.CURRENCY_ID))
        {
                return new Integer(getCurrencyId());
            }
          if (name.equals(PaymentItemPeer.ITEM_CURR_TOTAL))
        {
                return getItemCurrTotal();
            }
          if (name.equals(PaymentItemPeer.ITEM_TOTAL))
        {
                return getItemTotal();
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
                return new Integer(getPaymentItemId());
            }
              if (pos == 1)
        {
                return new Integer(getPaymentId());
            }
              if (pos == 2)
        {
                return new Integer(getSorderId());
            }
              if (pos == 3)
        {
                return new Integer(getCustomerId());
            }
              if (pos == 4)
        {
                return new Integer(getProjectId());
            }
              if (pos == 5)
        {
                return new Integer(getProductId());
            }
              if (pos == 6)
        {
                return getDescription();
            }
              if (pos == 7)
        {
                return getUnitPrice();
            }
              if (pos == 8)
        {
                return new Integer(getQuantity());
            }
              if (pos == 9)
        {
                return new Integer(getCurrencyId());
            }
              if (pos == 10)
        {
                return getItemCurrTotal();
            }
              if (pos == 11)
        {
                return getItemTotal();
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
          save(PaymentItemPeer.getMapBuilder()
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
                    PaymentItemPeer.doInsert((PaymentItem) this, con);
                    setNew(false);
                }
                else
                {
                    PaymentItemPeer.doUpdate((PaymentItem) this, con);
                }
            }

                      alreadyInSave = false;
        }
      }

                  
      /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param key paymentItemId ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
            setPaymentItemId(((NumberKey) key).intValue());
        }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
            setPaymentItemId(Integer.parseInt(key));
        }

  
    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
          return SimpleKey.keyFor(getPaymentItemId());
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
      public PaymentItem copy() throws TorqueException
    {
        return copyInto(new PaymentItem());
    }
  
    protected PaymentItem copyInto(PaymentItem copyObj) throws TorqueException
    {
          copyObj.setPaymentItemId(paymentItemId);
          copyObj.setPaymentId(paymentId);
          copyObj.setSorderId(sorderId);
          copyObj.setCustomerId(customerId);
          copyObj.setProjectId(projectId);
          copyObj.setProductId(productId);
          copyObj.setDescription(description);
          copyObj.setUnitPrice(unitPrice);
          copyObj.setQuantity(quantity);
          copyObj.setCurrencyId(currencyId);
          copyObj.setItemCurrTotal(itemCurrTotal);
          copyObj.setItemTotal(itemTotal);
  
                            copyObj.setPaymentItemId( 0);
                                                                              
                return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public PaymentItemPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("PaymentItem:\n");
        str.append("PaymentItemId = ")
               .append(getPaymentItemId())
             .append("\n");
        str.append("PaymentId = ")
               .append(getPaymentId())
             .append("\n");
        str.append("SorderId = ")
               .append(getSorderId())
             .append("\n");
        str.append("CustomerId = ")
               .append(getCustomerId())
             .append("\n");
        str.append("ProjectId = ")
               .append(getProjectId())
             .append("\n");
        str.append("ProductId = ")
               .append(getProductId())
             .append("\n");
        str.append("Description = ")
               .append(getDescription())
             .append("\n");
        str.append("UnitPrice = ")
               .append(getUnitPrice())
             .append("\n");
        str.append("Quantity = ")
               .append(getQuantity())
             .append("\n");
        str.append("CurrencyId = ")
               .append(getCurrencyId())
             .append("\n");
        str.append("ItemCurrTotal = ")
               .append(getItemCurrTotal())
             .append("\n");
        str.append("ItemTotal = ")
               .append(getItemTotal())
             .append("\n");
        return(str.toString());
    }
}