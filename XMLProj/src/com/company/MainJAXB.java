package com.company;

/*$ Step 1:
    Reference all the xsd:s in ApplicantProfile to compile them
    all in one xjc call.
    xjc -p com.company -d src schemas/ApplicantProfile.xsd
        parsing a schema...
        compiling a schema...
        com\company\ApplicantProfile.java
        com\company\CompanyInfo.java
        com\company\EmploymentRecord.java
        com\company\ObjectFactory.java
        com\company\ShortCV.java
        com\company\Transcript.java
        com\company\package-info.java*/

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

class JAXBProcessor {
    private ObjectFactory of = new ObjectFactory();

    private ShortCV shortCV;
    private CompanyInfo ci;
    private EmploymentRecord er;
    private Transcript tr;

    public JAXBProcessor() {
        File docSCV = new File("Resources/xml/ShortCV.xml");
        File docER = new File("Resources/xml/EmploymentRecord.xml");
        File docTR = new File("Resources/xml/Transcripts.xml");
        File docCI = new File("Resources/xml/CompanyInfo.xml");

        try {
            // Load shortCV
            JAXBContext jaxbContext1 = JAXBContext.newInstance(ShortCV.class);
            Unmarshaller jaxbUnmarshaller1 = jaxbContext1.createUnmarshaller();
            shortCV = (ShortCV) jaxbUnmarshaller1.unmarshal(docSCV);

            // Load CompanyInfo
            JAXBContext jaxbContext2 = JAXBContext.newInstance(CompanyInfo.class);
            Unmarshaller jaxbUnmarshaller2 = jaxbContext2.createUnmarshaller();
            ci = (CompanyInfo) jaxbUnmarshaller2.unmarshal(docCI);

            // Load EmploymentRecord
            JAXBContext jaxbContext3 = JAXBContext.newInstance(EmploymentRecord.class);
            Unmarshaller jaxbUnmarshaller3 = jaxbContext3.createUnmarshaller();
            er = (EmploymentRecord) jaxbUnmarshaller3.unmarshal(docER);

            // Load Transcript
            JAXBContext jaxbContext4 = JAXBContext.newInstance(Transcript.class);
            Unmarshaller jaxbUnmarshaller4 = jaxbContext4.createUnmarshaller();
            tr = (Transcript) jaxbUnmarshaller4.unmarshal(docTR);

            // Create an ApplicantProfile class
            ApplicantProfile ap = of.createApplicantProfile();
            // Copy the data from ShortCV except briefBio
            ApplicantProfile.Userdata ud = of.createApplicantProfileUserdata();
            ud.setFullname(shortCV.getFullname());
            ud.setPersonalLetter(shortCV.getPersonalLetter());
            ud.setPersonnummer(shortCV.getPersonnummer());
            ap.setUserdata(ud);
            // Copy the data from EmploymentRecord
            ApplicantProfile.EmploymentRecord apEr = of.createApplicantProfileEmploymentRecord();
            List<EmploymentRecord.Employment> empl = er.getEmployment();
            for (EmploymentRecord.Employment e: empl) {
                ApplicantProfile.EmploymentRecord.Employment e_new = of.createApplicantProfileEmploymentRecordEmployment();
                e_new.setFrom(e.getFrom());
                e_new.setTo(e.getTo());
                e_new.setCode(e.getCode());
                apEr.setEmployment(e_new); // FIX IT, not only one Employment, validate
            }
            ap.setEmploymentRecord(apEr);
            // Copy the data from Transcripts
            ApplicantProfile.Degrees deg = of.createApplicantProfileDegrees();
            List<Transcript.Degrees> transcript = tr.getDegrees();
            ApplicantProfile.Degrees.Degree deg_new = of.createApplicantProfileDegreesDegree();
            deg_new.setGPA(transcript.get(0).getDegree().getGPA());
            deg_new.setType(transcript.get(0).getDegree().getType());
            deg_new.setUniName(transcript.get(0).getDegree().getUniName());
            deg.setDegree(deg_new);
            ap.setDegrees(deg);
            // Output
            JAXBContext jc = JAXBContext.newInstance( ApplicantProfile.class );
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal( ap, new FileOutputStream( "Resources/output/ApplicantProfileJAX1.xml" ) );
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class MainJAXB {

    public static void main(String[] args) {
        JAXBProcessor jp = new JAXBProcessor();

    }
}
