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
  * [Wed May 04 09:10:56 CEST 2005]
  *
  */
public class PaymentItemMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.cream.om.map.PaymentItemMapBuilder";


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

        dbMap.addTable("PAYMENT_ITEM");
        TableMap tMap = dbMap.getTable("PAYMENT_ITEM");

        tMap.setPrimaryKeyMethod(TableMap.NATIVE);

        tMap.setPrimaryKeyMethodInfo("PAYMENT_ITEM_SEQ");

              tMap.addPrimaryKey("PAYMENT_ITEM.PAYMENT_ITEM_ID", new Integer(0));
                    tMap.addForeignKey(
                "PAYMENT_ITEM.PAYMENT_ID", new Integer(0) , "PAYMENT" ,
                "PAYMENT_ID");
                    tMap.addForeignKey(
                "PAYMENT_ITEM.SORDER_ID", new Integer(0) , "SORDER" ,
                "SORDER_ID");
                    tMap.addForeignKey(
                "PAYMENT_ITEM.CUSTOMER_ID", new Integer(0) , "CUSTOMER" ,
                "CUSTOMER_ID");
                    tMap.addForeignKey(
                "PAYMENT_ITEM.PROJECT_ID", new Integer(0) , "PROJECT" ,
                "PROJECT_ID");
                    tMap.addForeignKey(
                "PAYMENT_ITEM.PRODUCT_ID", new Integer(0) , "PRODUCT" ,
                "PRODUCT_ID");
                    tMap.addColumn("PAYMENT_ITEM.DESCRIPTION", "");
                    tMap.addColumn("PAYMENT_ITEM.UNIT_PRICE", new BigDecimal(0));
                    tMap.addColumn("PAYMENT_ITEM.QUANTITY", new Integer(0));
                    tMap.addForeignKey(
                "PAYMENT_ITEM.CURRENCY_ID", new Integer(0) , "CURRENCY" ,
                "CURRENCY_ID");
                    tMap.addColumn("PAYMENT_ITEM.ITEM_CURR_TOTAL", new BigDecimal(0));
                    tMap.addColumn("PAYMENT_ITEM.ITEM_TOTAL", new BigDecimal(0));
          }
}