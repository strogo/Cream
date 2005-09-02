package org.campware.cream.modules.upload;

import com.pz.reader.DataSet;
import java.io.*;
import java.util.*;

import org.apache.torque.TorqueException;
import org.apache.torque.util.Criteria;
import org.campware.cream.om.*;
import org.apache.turbine.util.RunData;
import org.apache.commons.fileupload.FileItem;
import org.apache.turbine.services.servlet.TurbineServlet;



public class UploadHandler {

    public UploadHandler() {
    }


//Handle uploaded files, output status string
    public static String doPost(RunData data)  {
        StringBuffer out = new StringBuffer();
        try {
          long tempo1;
          //   out = response.getWriter();

          Object[] enum = data.getParameters().getKeys();
          FileItem fileItem = data.getParameters().getFileItem("file");

          tempo1 = (new Date()).getTime();


          if (fileItem == null) {

            return "No Data!";
          }

          else {
//            String type = data.getParameters().getString("type").trim();
            int imptype = data.getParameters().getInt("type", 20);
            String path = data.getServletContext().getRealPath("/") + "uploads\\";

              //readin in the CSV file and parse it according to a specified schema
              DataSet ds = null;
              File mappingFile = null;
              File txtFile = null;
              try {

                if (imptype==10) {
//                  mappingFile = new File(path + "/CUSTOMER.pzmap.xml");
                  mappingFile = new File(TurbineServlet.getRealPath("/uploads/CUSTOMER.pzmap.xml"));
                }
                else {
//                  mappingFile = new File(path + "/PRODUCT.pzmap.xml");
                  mappingFile = new File(TurbineServlet.getRealPath("/uploads/PRODUCT.pzmap.xml"));
                }


//                txtFile = new File(TurbineServlet.getRealPath("/uploaded.file"));
//                fileItem.write(TurbineServlet.getRealPath(txtFile.getName()));
        		Date currDate= new Date();
        		String fileName= Integer.toString(currDate.hashCode());

                txtFile = new File(TurbineServlet.getRealPath("/uploads/" + fileName + ".file"));
                fileItem.write(txtFile);
                 
                //read in the file a
                ds = new DataSet(mappingFile, //mapping file here
                                 txtFile, //text file that is being parsed
                                 "	", //how the file is delimited
                                 "", //if the text is qualified, pass the qualifier here, otherwise leave empty
                                 false); //ignore the first row in the file if it contain column names

              }
              catch (Exception ex) {
                ex.printStackTrace();
                out.append("\\nError Parsing the CSV file: " + ex);
              }
              finally {
                try {
                  //delete the uploaded file
                  txtFile.delete();
                }
                catch (Exception ex) {
                  out.append("\\nError Deleting uploaded file: " + ex);
                }

              }

              int k = 0;
              if (imptype==10) {
                while (ds.next()) {

                  //skip the header
                  if ("WebUrl".equals(ds.getString("WebUrl")) && "Email".equals(ds.getString("Email"))){
                  continue;
                  }


                  Customer pr = new Customer();
                  try {

                    String st = ds.getString("Status");
                    int stat_ = 10;

                    if ("Active".equals(st)){
                    stat_ = 30;
                    }
                    else if
                    ("Inactive".equals(st)){
                     stat_ = 50;
                   }

                    pr.setStatus(stat_);

                    int typ_ = "Institution".equals(ds.getString("CustomerType")) ? 20: 10;
                    pr.setCustomerType(typ_);

                    pr.setPriority(30);


                    pr.setCustomerName1(ds.getString("CustomerName1"));
                    pr.setCustomerName2(ds.getString("CustomerName2"));
                    pr.setCustomerDisplay(ds.getString("CustomerDisplay"));
                    pr.setDear(ds.getString("Dear"));
                    pr.setAddress1(ds.getString("Address1"));
                    pr.setAddress2(ds.getString("Address2"));
                    pr.setCity(ds.getString("City"));
                    pr.setZip(ds.getString("Zip"));
                    pr.setState(ds.getString("State"));

                    pr.setCustomerCatId(getCustomerCatIdByName(ds.getString("CustomerCatName")));
                    pr.setCountryId(getCountryIdByName(ds.getString("CountryName")));
                    pr.setRegionId(getRegionIdByName(ds.getString("RegionName")));
                    pr.setLanguageId(getLanguageIdByName(ds.getString("LanguageName")));

                    pr.setPhone1(ds.getString("Phone1"));
                    pr.setPhone2(ds.getString("Phone2"));
                    pr.setFax(ds.getString("Fax"));
                    pr.setEmail(ds.getString("Email"));
                    pr.setSendNews(10);
                    pr.setWebUrl(ds.getString("WebUrl"));



                    String gender = ds.getString("Gender");

                   int gen  = 10;
                   if ("Male".equals(gender)){
                   gen = 20;
                   }
                   else if("Female".equals(gender)){
                   gen=30;
                   }

                   pr.setGender(gen);
                    pr.setEducationCatId(getEducationCatIdByName(ds.getString("EducationCatName")));
                    pr.setHouseholdCatId(getHouseholdCatIdByName(ds.getString("HouseholdCatName")));
//                    if (ds.getString("CustomerCode") != null &&
//                        ds.getString("CustomerCode").length() > 0) {
//                      pr.setCustomerCode(ds.getString("CustomerCode"));
//                    }
//                    else {
                      pr.setCustomerCode(getTempCode());
//                    }
                    pr.setCreated(new Date(System.currentTimeMillis()));
                    pr.setModified(new Date(System.currentTimeMillis()));
                    pr.setCreatedBy("Import Service");
                    pr.setModifiedBy("Import Service");
                    pr.save();
//                    if (ds.getString("CustomerCode") == null ||
//                        ds.getString("CustomerCode").length() == 0) {
                      pr.setCustomerCode(getRowCode("CU",
                          (new Integer(pr.getPrimaryKey().toString())).intValue()));
                      pr.save();
//                    }
                  }
                  catch (Exception ex1) {
                    out.append("\\nError while extracting line(Skipped!): " + ds.getRowNo() + " - " + ex1);
                    continue;
                  }
                  k++;
                }
              }
              else {
                while (ds.next()) {
                  Product pr = new Product();
                  try {
                    //skip the header
                    if ("WebUrl".equals(ds.getString("WebUrl")) ){
                    continue;
                    }


                    String status = ds.getString("Status");
                    String typ = ds.getString("ProductType");


                    int status_ = "Active".equals(status) ? 30 : 50;
                    int type_ = "Stock Item".equals(typ) ? 10: 50;

                    pr.setStatus(status_);
                    pr.setProductType(type_);

                    pr.setPriority(30);
                    pr.setProductCatId(getProductCatIdByName(ds.getString("ProductCatName")));
                    pr.setProductDescription(ds.getString("ProductDescription"));
                    pr.setProductDisplay(ds.getString("ProductDisplay"));
                    pr.setBasePrice(ds.getDouble("BasePrice"));
                    pr.setUomId(getUomIdByName(ds.getString("UomName")));
                    pr.setWebUrl(ds.getString("WebUrl"));
                    pr.setShowOnPricelist(20);
                    pr.setVendorId(getVendorIdByName(ds.getString("VendorName")));

                    pr.setProductCode(getTempCode());




                    pr.setCreated(new Date(System.currentTimeMillis()));
                    pr.setModified(new Date(System.currentTimeMillis()));
                    pr.setCreatedBy("Import Service");
                    pr.setModifiedBy("Import Service");
                    pr.save();
//                    if (ds.getString("ProductCode") == null ||
//                        ds.getString("ProductCode").length() == 0) {
                      pr.setProductCode(getRowCode("PR",
                          (new Integer(pr.getPrimaryKey().toString())).intValue()));
                      pr.save();
//                    }
                  }
                  catch (Exception ex1) {
                    out.append("\\nError while extracting line(Skipped!): " + ds.getRowNo() + " - " + ex1);
                    continue;
                  }
                  k++;
                }
              }
//              out.append("\\n\\nSummary:  ");
//              out.append("\\nType: " + type);
              out.append("\\nSuccessfuly written " + k + " rows to database.");

              long tempo2 = (new Date()).getTime();
              out.append("\\nIt took " + (double) (tempo2 - tempo1) / 1000D + " seconds to perform the upload.");


          }
        }
        catch (Exception ex2) {
          ex2.printStackTrace();
          out.append(ex2.getMessage());
        }

        return (out!=null?out.toString():"").replaceAll("\"","*").replaceAll("'","*");
    }

    private static int getCustomerCatIdByName(Object name){
      try {
      Criteria langcrit = new Criteria();
      langcrit.add(CustomerCategoryPeer.CUSTOMER_CAT_NAME, name, Criteria.EQUAL);

      List list = CustomerCategoryPeer.doSelect(langcrit);
      CustomerCategory item;
      for (Iterator iter = list.iterator(); iter.hasNext();
           ) {
        item = (CustomerCategory) iter.next();
        return item.getCustomerCatId();
      }
    }
    catch (TorqueException ex2) {
      ex2.printStackTrace();
    }
    return 1000;
  }

  private static int getCountryIdByName(Object name){
    try {
    Criteria langcrit = new Criteria();
    langcrit.add(CountryPeer.COUNTRY_NAME, name, Criteria.EQUAL);

    List list = CountryPeer.doSelect(langcrit);
    Country item;
    for (Iterator iter = list.iterator(); iter.hasNext();
         ) {
      item = (Country) iter.next();
      return item.getCountryId();
    }
  }
  catch (TorqueException ex2) {
    ex2.printStackTrace();
  }
  return 1000;
}



    private static  int getRegionIdByName(Object name) {
      try {
        Criteria langcrit = new Criteria();
        langcrit.add(RegionPeer.REGION_NAME, name, Criteria.EQUAL);

        List list = RegionPeer.doSelect(langcrit);
        Region item;
        for (Iterator iter = list.iterator(); iter.hasNext();
             ) {
          item = (Region) iter.next();
          return item.getRegionId();
        }
      }
      catch (TorqueException ex2) {
        ex2.printStackTrace();
      }
      return 1000;
    }

    private static  int getHouseholdCatIdByName(Object name) {
      try {
        Criteria langcrit = new Criteria();
        langcrit.add(HouseholdCategoryPeer.HOUSEHOLD_CAT_NAME, name, Criteria.EQUAL);

        List list = HouseholdCategoryPeer.doSelect(langcrit);
        HouseholdCategory item;
        for (Iterator iter = list.iterator(); iter.hasNext();
             ) {
          item = (HouseholdCategory) iter.next();
          return item.getHouseholdCatId();
        }
      }
      catch (TorqueException ex2) {
        ex2.printStackTrace();
      }
      return 1000;
    }


    private static  int getEducationCatIdByName(Object name) {
      try {
        Criteria langcrit = new Criteria();
        langcrit.add(EducationCategoryPeer.EDUCATION_CAT_NAME, name, Criteria.EQUAL);

        List list = EducationCategoryPeer.doSelect(langcrit);
        EducationCategory item;
        for (Iterator iter = list.iterator(); iter.hasNext();
             ) {
          item = (EducationCategory) iter.next();
          return item.getEducationCatId();
        }
      }
      catch (TorqueException ex2) {
        ex2.printStackTrace();
      }
      return 1000;
    }



    private static  int getLanguageIdByName(Object name) {
      try {
        Criteria langcrit = new Criteria();
        langcrit.add(LanguagePeer.LANGUAGE_NAME, name, Criteria.EQUAL);

        List list = LanguagePeer.doSelect(langcrit);
        Language item;
        for (Iterator iter = list.iterator(); iter.hasNext();
             ) {
          item = (Language) iter.next();
          return item.getLanguageId();
        }
      }
      catch (TorqueException ex2) {
        ex2.printStackTrace();
      }
      return 1000;
    }


    private static  int getProductCatIdByName(Object name) {
      try {
        Criteria langcrit = new Criteria();
        langcrit.add(ProductCategoryPeer.PRODUCT_CAT_NAME, name, Criteria.EQUAL);

        List list = ProductCategoryPeer.doSelect(langcrit);
        ProductCategory item;
        for (Iterator iter = list.iterator(); iter.hasNext();
             ) {
          item = (ProductCategory) iter.next();
          return item.getProductCatId();
        }
      }
      catch (TorqueException ex2) {
        ex2.printStackTrace();
      }
      return 1000;
    }

    private static  int getVendorIdByName(Object name) {
    try {
      Criteria langcrit = new Criteria();
      langcrit.add(VendorPeer.VENDOR_NAME, name, Criteria.EQUAL);

      List list = VendorPeer.doSelect(langcrit);
      Vendor item;
      for (Iterator iter = list.iterator(); iter.hasNext();
           ) {
        item = (Vendor) iter.next();
        return item.getVendorId();
      }
    }
    catch (TorqueException ex2) {
      ex2.printStackTrace();
    }
    return 1000;
  }


  private static  int getUomIdByName(Object name) {
  try {
    Criteria langcrit = new Criteria();
    langcrit.add(UomPeer.UOM_NAME, name, Criteria.EQUAL);

    List list = UomPeer.doSelect(langcrit);
    Uom item;
    for (Iterator iter = list.iterator(); iter.hasNext();
         ) {
      item = (Uom) iter.next();
      return item.getUomId();
    }
  }
  catch (TorqueException ex2) {
    ex2.printStackTrace();
  }
  return 1000;
}



    private static String getRowCode(String s, int i) {
        String is = new String();
        for(is = Integer.toString(i); is.length() < 7; is = "0" + is);
        is = s + is;
        return is;
    }

    private static  String getTempCode() {
        Date currDate = new Date();
        return Integer.toString(currDate.hashCode());
    }
}
