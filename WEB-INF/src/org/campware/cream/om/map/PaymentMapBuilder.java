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
public class PaymentMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.cream.om.map.PaymentMapBuilder";


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

        dbMap.addTable("PAYMENT");
        TableMap tMap = dbMap.getTable("PAYMENT");

        tMap.setPrimaryKeyMethod(TableMap.NATIVE);

        tMap.setPrimaryKeyMethodInfo("PAYMENT_SEQ");

              tMap.addPrimaryKey("PAYMENT.PAYMENT_ID", new Integer(0));
                    tMap.addColumn("PAYMENT.PAYMENT_CODE", "");
                    tMap.addColumn("PAYMENT.STATUS", new Integer(0));
                    tMap.addColumn("PAYMENT.PRIORITY", new Integer(0));
                    tMap.addColumn("PAYMENT.ISSUED_DATE", new Date());
                    tMap.addColumn("PAYMENT.CLOSED_DATE", new Date());
                    tMap.addForeignKey(
                "PAYMENT.CUSTOMER_ID", new Integer(0) , "CUSTOMER" ,
                "CUSTOMER_ID");
                    tMap.addForeignKey(
                "PAYMENT.PROJECT_ID", new Integer(0) , "PROJECT" ,
                "PROJECT_ID");
                    tMap.addForeignKey(
                "PAYMENT.SORDER_ID", new Integer(0) , "SORDER" ,
                "SORDER_ID");
                    tMap.addColumn("PAYMENT.INVOICE_CODE", "");
                    tMap.addColumn("PAYMENT.PAY_TERM", new Integer(0));
                    tMap.addColumn("PAYMENT.PAY_METHOD", new Integer(0));
                    tMap.addForeignKey(
                "PAYMENT.CURRENCY_ID", new Integer(0) , "CURRENCY" ,
                "CURRENCY_ID");
                    tMap.addColumn("PAYMENT.CURRENCY_RATE", new BigDecimal(0));
                    tMap.addColumn("PAYMENT.CURRENCY_AMOUNT", new BigDecimal(0));
                    tMap.addColumn("PAYMENT.TOTAL_AMOUNT", new BigDecimal(0));
                    tMap.addColumn("PAYMENT.SUBJECT", "");
                    tMap.addColumn("PAYMENT.NOTES", "");
                    tMap.addColumn("PAYMENT.CREATED", new Date());
                    tMap.addColumn("PAYMENT.MODIFIED", new Date());
                    tMap.addColumn("PAYMENT.CREATED_BY", "");
                    tMap.addColumn("PAYMENT.MODIFIED_BY", "");
          }
}
