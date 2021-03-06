package org.campware.cream.om.map;

import java.util.Date;
import java.math.BigDecimal;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.DatabaseMap;
import org.apache.torque.map.TableMap;

/**
  *  This class was autogenerated by Torque on:
  *
  * [Fri Jan 26 03:28:50 CET 2007]
  *
  */
public class PrintSubscriptionMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.cream.om.map.PrintSubscriptionMapBuilder";


    /**
     * The database map.
     */
    private DatabaseMap dbMap = null;

    /**
     * Tells us if this DatabaseMapBuilder is built so that we
     * don't have to re-build it every time.
     *
     * @return true if this DatabaseMapBuilder is built
     */
    public boolean isBuilt()
    {
        return (dbMap != null);
    }

    /**
     * Gets the databasemap this map builder built.
     *
     * @return the databasemap
     */
    public DatabaseMap getDatabaseMap()
    {
        return this.dbMap;
    }

    /**
     * The doBuild() method builds the DatabaseMap
     *
     * @throws TorqueException
     */
    public void doBuild() throws TorqueException
    {
        dbMap = Torque.getDatabaseMap("cream");

        dbMap.addTable("PRINT_SUBSCRIPTION");
        TableMap tMap = dbMap.getTable("PRINT_SUBSCRIPTION");

        tMap.setPrimaryKeyMethod(TableMap.NATIVE);


              tMap.addPrimaryKey("PRINT_SUBSCRIPTION.PRINT_SUBS_ID", new Integer(0));
                    tMap.addColumn("PRINT_SUBSCRIPTION.PRINT_SUBS_CODE", "");
                    tMap.addColumn("PRINT_SUBSCRIPTION.STATUS", new Integer(0));
                    tMap.addColumn("PRINT_SUBSCRIPTION.PRIORITY", new Integer(0));
                    tMap.addColumn("PRINT_SUBSCRIPTION.ISSUED_DATE", new Date());
                    tMap.addColumn("PRINT_SUBSCRIPTION.CLOSED_DATE", new Date());
                    tMap.addForeignKey(
                "PRINT_SUBSCRIPTION.SORDER_ID", new Integer(0) , "SORDER" ,
                "SORDER_ID");
                    tMap.addForeignKey(
                "PRINT_SUBSCRIPTION.CUSTOMER_ID", new Integer(0) , "CUSTOMER" ,
                "CUSTOMER_ID");
                    tMap.addForeignKey(
                "PRINT_SUBSCRIPTION.RECIPIENT_ID", new Integer(0) , "CUSTOMER" ,
                "CUSTOMER_ID");
                    tMap.addForeignKey(
                "PRINT_SUBSCRIPTION.CARRIER_ID", new Integer(0) , "CARRIER" ,
                "CARRIER_ID");
                    tMap.addForeignKey(
                "PRINT_SUBSCRIPTION.PROJECT_ID", new Integer(0) , "PROJECT" ,
                "PROJECT_ID");
                    tMap.addForeignKey(
                "PRINT_SUBSCRIPTION.PRODUCT_ID", new Integer(0) , "PRODUCT" ,
                "PRODUCT_ID");
                    tMap.addColumn("PRINT_SUBSCRIPTION.QUANTITY", new Integer(0));
                    tMap.addColumn("PRINT_SUBSCRIPTION.START_DATE", new Date());
                    tMap.addColumn("PRINT_SUBSCRIPTION.END_DATE", new Date());
                    tMap.addColumn("PRINT_SUBSCRIPTION.SUBJECT", "");
                    tMap.addColumn("PRINT_SUBSCRIPTION.NOTES", "");
                    tMap.addColumn("PRINT_SUBSCRIPTION.CREATED", new Date());
                    tMap.addColumn("PRINT_SUBSCRIPTION.MODIFIED", new Date());
                    tMap.addColumn("PRINT_SUBSCRIPTION.CREATED_BY", "");
                    tMap.addColumn("PRINT_SUBSCRIPTION.MODIFIED_BY", "");
          }
}
