package org.campware.cream.om;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.TableMap;
import org.apache.torque.om.DateKey;
import org.apache.torque.om.NumberKey;
import org.apache.torque.om.StringKey;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.util.BasePeer;
import org.apache.torque.util.Criteria;

import com.workingdogs.village.DataSetException;
import com.workingdogs.village.QueryDataSet;
import com.workingdogs.village.Record;

// Local classes
import org.campware.cream.om.map.*;


  
  
/**
 * This class was autogenerated by Torque on:
 *
 * [Fri Jan 26 03:28:50 CET 2007]
 *
 */
public abstract class BaseNewsSubscriptionPeer
    extends BasePeer
{

    /** the default database name for this class */
    public static final String DATABASE_NAME = "cream";

     /** the table name for this class */
    public static final String TABLE_NAME = "NEWS_SUBSCRIPTION";

    /**
     * @return the map builder for this peer
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static MapBuilder getMapBuilder()
        throws TorqueException
    {
        return getMapBuilder(NewsSubscriptionMapBuilder.CLASS_NAME);
    }

      /** the column name for the NEWS_SUBS_ID field */
    public static final String NEWS_SUBS_ID;
      /** the column name for the NEWS_SUBS_CODE field */
    public static final String NEWS_SUBS_CODE;
      /** the column name for the STATUS field */
    public static final String STATUS;
      /** the column name for the PRIORITY field */
    public static final String PRIORITY;
      /** the column name for the ISSUED_DATE field */
    public static final String ISSUED_DATE;
      /** the column name for the CLOSED_DATE field */
    public static final String CLOSED_DATE;
      /** the column name for the EMAIL field */
    public static final String EMAIL;
      /** the column name for the PROJECT_ID field */
    public static final String PROJECT_ID;
      /** the column name for the PRODUCT_ID field */
    public static final String PRODUCT_ID;
      /** the column name for the SUBJECT field */
    public static final String SUBJECT;
      /** the column name for the NOTES field */
    public static final String NOTES;
      /** the column name for the CREATED field */
    public static final String CREATED;
      /** the column name for the MODIFIED field */
    public static final String MODIFIED;
      /** the column name for the CREATED_BY field */
    public static final String CREATED_BY;
      /** the column name for the MODIFIED_BY field */
    public static final String MODIFIED_BY;
  
    static
    {
          NEWS_SUBS_ID = "NEWS_SUBSCRIPTION.NEWS_SUBS_ID";
          NEWS_SUBS_CODE = "NEWS_SUBSCRIPTION.NEWS_SUBS_CODE";
          STATUS = "NEWS_SUBSCRIPTION.STATUS";
          PRIORITY = "NEWS_SUBSCRIPTION.PRIORITY";
          ISSUED_DATE = "NEWS_SUBSCRIPTION.ISSUED_DATE";
          CLOSED_DATE = "NEWS_SUBSCRIPTION.CLOSED_DATE";
          EMAIL = "NEWS_SUBSCRIPTION.EMAIL";
          PROJECT_ID = "NEWS_SUBSCRIPTION.PROJECT_ID";
          PRODUCT_ID = "NEWS_SUBSCRIPTION.PRODUCT_ID";
          SUBJECT = "NEWS_SUBSCRIPTION.SUBJECT";
          NOTES = "NEWS_SUBSCRIPTION.NOTES";
          CREATED = "NEWS_SUBSCRIPTION.CREATED";
          MODIFIED = "NEWS_SUBSCRIPTION.MODIFIED";
          CREATED_BY = "NEWS_SUBSCRIPTION.CREATED_BY";
          MODIFIED_BY = "NEWS_SUBSCRIPTION.MODIFIED_BY";
          if (Torque.isInit())
        {
            try
            {
                getMapBuilder(NewsSubscriptionMapBuilder.CLASS_NAME);
            }
            catch (Exception e)
            {
                log.error("Could not initialize Peer", e);
            }
        }
        else
        {
            Torque.registerMapBuilder(NewsSubscriptionMapBuilder.CLASS_NAME);
        }
    }
 
    /** number of columns for this peer */
    public static final int numColumns =  15;

    /** A class that can be returned by this peer. */
    protected static final String CLASSNAME_DEFAULT =
        "org.campware.cream.om.NewsSubscription";

    /** A class that can be returned by this peer. */
    protected static final Class CLASS_DEFAULT = initClass(CLASSNAME_DEFAULT);

    /**
     * Class object initialization method.
     *
     * @param className name of the class to initialize
     * @return the initialized class
     */
    private static Class initClass(String className)
    {
        Class c = null;
        try
        {
            c = Class.forName(className);
        }
        catch (Throwable t)
        {
            log.error("A FATAL ERROR has occurred which should not "
                + "have happened under any circumstance.  Please notify "
                + "the Torque developers <torque-dev@db.apache.org> "
                + "and give as many details as possible (including the error "
                + "stack trace).", t);

            // Error objects should always be propogated.
            if (t instanceof Error)
            {
                throw (Error) t.fillInStackTrace();
            }
        }
        return c;
    }

    /**
     * Get the list of objects for a ResultSet.  Please not that your
     * resultset MUST return columns in the right order.  You can use
     * getFieldNames() in BaseObject to get the correct sequence.
     *
     * @param results the ResultSet
     * @return the list of objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List resultSet2Objects(java.sql.ResultSet results)
            throws TorqueException
    {
        try
        {
            QueryDataSet qds = null;
            List rows = null;
            try
            {
                qds = new QueryDataSet(results);
                rows = getSelectResults(qds);
            }
            finally
            {
                if (qds != null)
                {
                    qds.close();
                }
            }

            return populateObjects(rows);
        }
        catch (SQLException e)
        {
            throw new TorqueException(e);
        }
        catch (DataSetException e)
        {
            throw new TorqueException(e);
        }
    }


  
    /**
     * Method to do inserts.
     *
     * @param criteria object used to create the INSERT statement.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static ObjectKey doInsert(Criteria criteria)
        throws TorqueException
    {
        return BaseNewsSubscriptionPeer
            .doInsert(criteria, (Connection) null);
    }

    /**
     * Method to do inserts.  This method is to be used during a transaction,
     * otherwise use the doInsert(Criteria) method.  It will take care of
     * the connection details internally.
     *
     * @param criteria object used to create the INSERT statement.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static ObjectKey doInsert(Criteria criteria, Connection con)
        throws TorqueException
    {
                                                                                            
        setDbName(criteria);

        if (con == null)
        {
            return BasePeer.doInsert(criteria);
        }
        else
        {
            return BasePeer.doInsert(criteria, con);
        }
    }

    /**
     * Add all the columns needed to create a new object.
     *
     * @param criteria object containing the columns to add.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void addSelectColumns(Criteria criteria)
            throws TorqueException
    {
          criteria.addSelectColumn(NEWS_SUBS_ID);
          criteria.addSelectColumn(NEWS_SUBS_CODE);
          criteria.addSelectColumn(STATUS);
          criteria.addSelectColumn(PRIORITY);
          criteria.addSelectColumn(ISSUED_DATE);
          criteria.addSelectColumn(CLOSED_DATE);
          criteria.addSelectColumn(EMAIL);
          criteria.addSelectColumn(PROJECT_ID);
          criteria.addSelectColumn(PRODUCT_ID);
          criteria.addSelectColumn(SUBJECT);
          criteria.addSelectColumn(NOTES);
          criteria.addSelectColumn(CREATED);
          criteria.addSelectColumn(MODIFIED);
          criteria.addSelectColumn(CREATED_BY);
          criteria.addSelectColumn(MODIFIED_BY);
      }

    /**
     * Create a new object of type cls from a resultset row starting
     * from a specified offset.  This is done so that you can select
     * other rows than just those needed for this object.  You may
     * for example want to create two objects from the same row.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static NewsSubscription row2Object(Record row,
                                             int offset,
                                             Class cls)
        throws TorqueException
    {
        try
        {
            NewsSubscription obj = (NewsSubscription) cls.newInstance();
            NewsSubscriptionPeer.populateObject(row, offset, obj);
                  obj.setModified(false);
              obj.setNew(false);

            return obj;
        }
        catch (InstantiationException e)
        {
            throw new TorqueException(e);
        }
        catch (IllegalAccessException e)
        {
            throw new TorqueException(e);
        }
    }

    /**
     * Populates an object from a resultset row starting
     * from a specified offset.  This is done so that you can select
     * other rows than just those needed for this object.  You may
     * for example want to create two objects from the same row.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void populateObject(Record row,
                                      int offset,
                                      NewsSubscription obj)
        throws TorqueException
    {
        try
        {
                obj.setNewsSubsId(row.getValue(offset + 0).asInt());
                  obj.setNewsSubsCode(row.getValue(offset + 1).asString());
                  obj.setStatus(row.getValue(offset + 2).asInt());
                  obj.setPriority(row.getValue(offset + 3).asInt());
                  obj.setIssuedDate(row.getValue(offset + 4).asUtilDate());
                  obj.setClosedDate(row.getValue(offset + 5).asUtilDate());
                  obj.setEmail(row.getValue(offset + 6).asString());
                  obj.setProjectId(row.getValue(offset + 7).asInt());
                  obj.setProductId(row.getValue(offset + 8).asInt());
                  obj.setSubject(row.getValue(offset + 9).asString());
                  obj.setNotes(row.getValue(offset + 10).asString());
                  obj.setCreated(row.getValue(offset + 11).asUtilDate());
                  obj.setModified(row.getValue(offset + 12).asUtilDate());
                  obj.setCreatedBy(row.getValue(offset + 13).asString());
                  obj.setModifiedBy(row.getValue(offset + 14).asString());
              }
        catch (DataSetException e)
        {
            throw new TorqueException(e);
        }
    }

    /**
     * Method to do selects.
     *
     * @param criteria object used to create the SELECT statement.
     * @return List of selected Objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelect(Criteria criteria) throws TorqueException
    {
        return populateObjects(doSelectVillageRecords(criteria));
    }

    /**
     * Method to do selects within a transaction.
     *
     * @param criteria object used to create the SELECT statement.
     * @param con the connection to use
     * @return List of selected Objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelect(Criteria criteria, Connection con)
        throws TorqueException
    {
        return populateObjects(doSelectVillageRecords(criteria, con));
    }

    /**
     * Grabs the raw Village records to be formed into objects.
     * This method handles connections internally.  The Record objects
     * returned by this method should be considered readonly.  Do not
     * alter the data and call save(), your results may vary, but are
     * certainly likely to result in hard to track MT bugs.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelectVillageRecords(Criteria criteria)
        throws TorqueException
    {
        return BaseNewsSubscriptionPeer
            .doSelectVillageRecords(criteria, (Connection) null);
    }

    /**
     * Grabs the raw Village records to be formed into objects.
     * This method should be used for transactions
     *
     * @param criteria object used to create the SELECT statement.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelectVillageRecords(Criteria criteria, Connection con)
        throws TorqueException
    {
        if (criteria.getSelectColumns().size() == 0)
        {
            addSelectColumns(criteria);
        }

                                                                                            
        setDbName(criteria);

        // BasePeer returns a List of Value (Village) arrays.  The array
        // order follows the order columns were placed in the Select clause.
        if (con == null)
        {
            return BasePeer.doSelect(criteria);
        }
        else
        {
            return BasePeer.doSelect(criteria, con);
        }
    }

    /**
     * The returned List will contain objects of the default type or
     * objects that inherit from the default.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List populateObjects(List records)
        throws TorqueException
    {
        List results = new ArrayList(records.size());

        // populate the object(s)
        for (int i = 0; i < records.size(); i++)
        {
            Record row = (Record) records.get(i);
              results.add(NewsSubscriptionPeer.row2Object(row, 1,
                NewsSubscriptionPeer.getOMClass()));
          }
        return results;
    }
 

    /**
     * The class that the Peer will make instances of.
     * If the BO is abstract then you must implement this method
     * in the BO.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static Class getOMClass()
        throws TorqueException
    {
        return CLASS_DEFAULT;
    }

    /**
     * Method to do updates.
     *
     * @param criteria object containing data that is used to create the UPDATE
     *        statement.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(Criteria criteria) throws TorqueException
    {
         BaseNewsSubscriptionPeer
            .doUpdate(criteria, (Connection) null);
    }

    /**
     * Method to do updates.  This method is to be used during a transaction,
     * otherwise use the doUpdate(Criteria) method.  It will take care of
     * the connection details internally.
     *
     * @param criteria object containing data that is used to create the UPDATE
     *        statement.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(Criteria criteria, Connection con)
        throws TorqueException
    {
        Criteria selectCriteria = new Criteria(DATABASE_NAME, 2);
                   selectCriteria.put(NEWS_SUBS_ID, criteria.remove(NEWS_SUBS_ID));
                                                                                                                                                  
        setDbName(criteria);

        if (con == null)
        {
            BasePeer.doUpdate(selectCriteria, criteria);
        }
        else
        {
            BasePeer.doUpdate(selectCriteria, criteria, con);
        }
    }

    /**
     * Method to do deletes.
     *
     * @param criteria object containing data that is used DELETE from database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
     public static void doDelete(Criteria criteria) throws TorqueException
     {
         NewsSubscriptionPeer
            .doDelete(criteria, (Connection) null);
     }

    /**
     * Method to do deletes.  This method is to be used during a transaction,
     * otherwise use the doDelete(Criteria) method.  It will take care of
     * the connection details internally.
     *
     * @param criteria object containing data that is used DELETE from database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
     public static void doDelete(Criteria criteria, Connection con)
        throws TorqueException
     {
                                                                                            
        setDbName(criteria);

        if (con == null)
        {
            BasePeer.doDelete(criteria);
        }
        else
        {
            BasePeer.doDelete(criteria, con);
        }
     }

    /**
     * Method to do selects
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List doSelect(NewsSubscription obj) throws TorqueException
    {
        return doSelect(buildSelectCriteria(obj));
    }

    /**
     * Method to do inserts
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doInsert(NewsSubscription obj) throws TorqueException
    {
          obj.setPrimaryKey(doInsert(buildCriteria(obj)));
          obj.setNew(false);
        obj.setModified(false);
    }

    /**
     * @param obj the data object to update in the database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(NewsSubscription obj) throws TorqueException
    {
        doUpdate(buildCriteria(obj));
        obj.setModified(false);
    }

    /**
     * @param obj the data object to delete in the database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(NewsSubscription obj) throws TorqueException
    {
        doDelete(buildSelectCriteria(obj));
    }

    /**
     * Method to do inserts.  This method is to be used during a transaction,
     * otherwise use the doInsert(NewsSubscription) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to insert into the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doInsert(NewsSubscription obj, Connection con)
        throws TorqueException
    {
          obj.setPrimaryKey(doInsert(buildCriteria(obj), con));
          obj.setNew(false);
        obj.setModified(false);
    }

    /**
     * Method to do update.  This method is to be used during a transaction,
     * otherwise use the doUpdate(NewsSubscription) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to update in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(NewsSubscription obj, Connection con)
        throws TorqueException
    {
        doUpdate(buildCriteria(obj), con);
        obj.setModified(false);
    }

    /**
     * Method to delete.  This method is to be used during a transaction,
     * otherwise use the doDelete(NewsSubscription) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to delete in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(NewsSubscription obj, Connection con)
        throws TorqueException
    {
        doDelete(buildSelectCriteria(obj), con);
    }

    /**
     * Method to do deletes.
     *
     * @param pk ObjectKey that is used DELETE from database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(ObjectKey pk) throws TorqueException
    {
        BaseNewsSubscriptionPeer
           .doDelete(pk, (Connection) null);
    }

    /**
     * Method to delete.  This method is to be used during a transaction,
     * otherwise use the doDelete(ObjectKey) method.  It will take
     * care of the connection details internally.
     *
     * @param pk the primary key for the object to delete in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(ObjectKey pk, Connection con)
        throws TorqueException
    {
        doDelete(buildCriteria(pk), con);
    }

    /** Build a Criteria object from an ObjectKey */
    public static Criteria buildCriteria( ObjectKey pk )
    {
        Criteria criteria = new Criteria();
              criteria.add(NEWS_SUBS_ID, pk);
          return criteria;
     }

    /** Build a Criteria object from the data object for this peer */
    public static Criteria buildCriteria( NewsSubscription obj )
    {
        Criteria criteria = new Criteria(DATABASE_NAME);
              if (!obj.isNew())
            criteria.add(NEWS_SUBS_ID, obj.getNewsSubsId());
              criteria.add(NEWS_SUBS_CODE, obj.getNewsSubsCode());
              criteria.add(STATUS, obj.getStatus());
              criteria.add(PRIORITY, obj.getPriority());
              criteria.add(ISSUED_DATE, obj.getIssuedDate());
              criteria.add(CLOSED_DATE, obj.getClosedDate());
              criteria.add(EMAIL, obj.getEmail());
              criteria.add(PROJECT_ID, obj.getProjectId());
              criteria.add(PRODUCT_ID, obj.getProductId());
              criteria.add(SUBJECT, obj.getSubject());
              criteria.add(NOTES, obj.getNotes());
              criteria.add(CREATED, obj.getCreated());
              criteria.add(MODIFIED, obj.getModified());
              criteria.add(CREATED_BY, obj.getCreatedBy());
              criteria.add(MODIFIED_BY, obj.getModifiedBy());
          return criteria;
    }

    /** Build a Criteria object from the data object for this peer, skipping all binary columns */
    public static Criteria buildSelectCriteria( NewsSubscription obj )
    {
        Criteria criteria = new Criteria(DATABASE_NAME);
              if (!obj.isNew())
                    criteria.add(NEWS_SUBS_ID, obj.getNewsSubsId());
                          criteria.add(NEWS_SUBS_CODE, obj.getNewsSubsCode());
                          criteria.add(STATUS, obj.getStatus());
                          criteria.add(PRIORITY, obj.getPriority());
                          criteria.add(ISSUED_DATE, obj.getIssuedDate());
                          criteria.add(CLOSED_DATE, obj.getClosedDate());
                          criteria.add(EMAIL, obj.getEmail());
                          criteria.add(PROJECT_ID, obj.getProjectId());
                          criteria.add(PRODUCT_ID, obj.getProductId());
                          criteria.add(SUBJECT, obj.getSubject());
                          criteria.add(NOTES, obj.getNotes());
                          criteria.add(CREATED, obj.getCreated());
                          criteria.add(MODIFIED, obj.getModified());
                          criteria.add(CREATED_BY, obj.getCreatedBy());
                          criteria.add(MODIFIED_BY, obj.getModifiedBy());
              return criteria;
    }
 
    
        /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @throws NoRowsException Primary key was not found in database.
     * @throws TooManyRowsException Primary key was not found in database.
     */
    public static NewsSubscription retrieveByPK(int pk)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        return retrieveByPK(SimpleKey.keyFor(pk));
    }

    /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @throws NoRowsException Primary key was not found in database.
     * @throws TooManyRowsException Primary key was not found in database.
     */
    public static NewsSubscription retrieveByPK(int pk, Connection con)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        return retrieveByPK(SimpleKey.keyFor(pk), con);
    }
  
    /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @throws NoRowsException Primary key was not found in database.
     * @throws TooManyRowsException Primary key was not found in database.
     */
    public static NewsSubscription retrieveByPK(ObjectKey pk)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        Connection db = null;
        NewsSubscription retVal = null;
        try
        {
            db = Torque.getConnection(DATABASE_NAME);
            retVal = retrieveByPK(pk, db);
        }
        finally
        {
            Torque.closeConnection(db);
        }
        return(retVal);
    }

    /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @throws NoRowsException Primary key was not found in database.
     * @throws TooManyRowsException Primary key was not found in database.
     */
    public static NewsSubscription retrieveByPK(ObjectKey pk, Connection con)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        Criteria criteria = buildCriteria(pk);
        List v = doSelect(criteria, con);
        if (v.size() == 0)
        {
            throw new NoRowsException("Failed to select a row.");
        }
        else if (v.size() > 1)
        {
            throw new TooManyRowsException("Failed to select only one row.");
        }
        else
        {
            return (NewsSubscription)v.get(0);
        }
    }

    /**
     * Retrieve a multiple objects by pk
     *
     * @param pks List of primary keys
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List retrieveByPKs(List pks)
        throws TorqueException
    {
        Connection db = null;
        List retVal = null;
        try
        {
           db = Torque.getConnection(DATABASE_NAME);
           retVal = retrieveByPKs(pks, db);
        }
        finally
        {
            Torque.closeConnection(db);
        }
        return(retVal);
    }

    /**
     * Retrieve a multiple objects by pk
     *
     * @param pks List of primary keys
     * @param dbcon the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List retrieveByPKs( List pks, Connection dbcon )
        throws TorqueException
    {
        List objs = null;
        if (pks == null || pks.size() == 0)
        {
            objs = new LinkedList();
        }
        else
        {
            Criteria criteria = new Criteria();
              criteria.addIn( NEWS_SUBS_ID, pks );
          objs = doSelect(criteria, dbcon);
        }
        return objs;
    }

 



            
                                              
                
                

    /**
     * selects a collection of NewsSubscription objects pre-filled with their
     * Project objects.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in NewsSubscriptionPeer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static List doSelectJoinProject(Criteria criteria)
        throws TorqueException
    {
        setDbName(criteria);

        NewsSubscriptionPeer.addSelectColumns(criteria);
        int offset = numColumns + 1;
        ProjectPeer.addSelectColumns(criteria);


                        criteria.addJoin(NewsSubscriptionPeer.PROJECT_ID,
            ProjectPeer.PROJECT_ID);
        

                                                                                                                                                                                                                                                                                      
        List rows = BasePeer.doSelect(criteria);
        List results = new ArrayList();

        for (int i = 0; i < rows.size(); i++)
        {
            Record row = (Record) rows.get(i);

                            Class omClass = NewsSubscriptionPeer.getOMClass();
                    NewsSubscription obj1 = (NewsSubscription) NewsSubscriptionPeer
                .row2Object(row, 1, omClass);
                     omClass = ProjectPeer.getOMClass();
                    Project obj2 = (Project)ProjectPeer
                .row2Object(row, offset, omClass);

            boolean newObject = true;
            for (int j = 0; j < results.size(); j++)
            {
                NewsSubscription temp_obj1 = (NewsSubscription)results.get(j);
                Project temp_obj2 = (Project)temp_obj1.getProject();
                if (temp_obj2.getPrimaryKey().equals(obj2.getPrimaryKey()))
                {
                    newObject = false;
                              temp_obj2.addNewsSubscription(obj1);
                              break;
                }
            }
                      if (newObject)
            {
                obj2.initNewsSubscriptions();
                obj2.addNewsSubscription(obj1);
            }
                      results.add(obj1);
        }
        return results;
    }
                                                            
                
                

    /**
     * selects a collection of NewsSubscription objects pre-filled with their
     * Product objects.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in NewsSubscriptionPeer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static List doSelectJoinProduct(Criteria criteria)
        throws TorqueException
    {
        setDbName(criteria);

        NewsSubscriptionPeer.addSelectColumns(criteria);
        int offset = numColumns + 1;
        ProductPeer.addSelectColumns(criteria);


                        criteria.addJoin(NewsSubscriptionPeer.PRODUCT_ID,
            ProductPeer.PRODUCT_ID);
        

                                                                                                                                                                                                                                                                                      
        List rows = BasePeer.doSelect(criteria);
        List results = new ArrayList();

        for (int i = 0; i < rows.size(); i++)
        {
            Record row = (Record) rows.get(i);

                            Class omClass = NewsSubscriptionPeer.getOMClass();
                    NewsSubscription obj1 = (NewsSubscription) NewsSubscriptionPeer
                .row2Object(row, 1, omClass);
                     omClass = ProductPeer.getOMClass();
                    Product obj2 = (Product)ProductPeer
                .row2Object(row, offset, omClass);

            boolean newObject = true;
            for (int j = 0; j < results.size(); j++)
            {
                NewsSubscription temp_obj1 = (NewsSubscription)results.get(j);
                Product temp_obj2 = (Product)temp_obj1.getProduct();
                if (temp_obj2.getPrimaryKey().equals(obj2.getPrimaryKey()))
                {
                    newObject = false;
                              temp_obj2.addNewsSubscription(obj1);
                              break;
                }
            }
                      if (newObject)
            {
                obj2.initNewsSubscriptions();
                obj2.addNewsSubscription(obj1);
            }
                      results.add(obj1);
        }
        return results;
    }
                    
  
    
  
      /**
     * Returns the TableMap related to this peer.  This method is not
     * needed for general use but a specific application could have a need.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static TableMap getTableMap()
        throws TorqueException
    {
        return Torque.getDatabaseMap(DATABASE_NAME).getTable(TABLE_NAME);
    }
   
    private static void setDbName(Criteria crit)
    {
        // Set the correct dbName if it has not been overridden
        // crit.getDbName will return the same object if not set to
        // another value so == check is okay and faster
        if (crit.getDbName() == Torque.getDefaultDB())
        {
            crit.setDbName(DATABASE_NAME);
        }
    }
}
