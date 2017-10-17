
import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.text.html.HTMLDocument.Iterator;
import javax.xml.namespace.QName;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import net.bpelunit.model.bpel.BpelFactory;
import net.bpelunit.model.bpel.IActivity;
import net.bpelunit.model.bpel.IAssign;
import net.bpelunit.model.bpel.IBpelObject;
import net.bpelunit.model.bpel.ICopy;
import net.bpelunit.model.bpel.IDocumentation;
import net.bpelunit.model.bpel.IFlow;
import net.bpelunit.model.bpel.IImport;
import net.bpelunit.model.bpel.IInvoke;
import net.bpelunit.model.bpel.IPartnerLink;
import net.bpelunit.model.bpel.IProcess;
import net.bpelunit.model.bpel.IReceive;
import net.bpelunit.model.bpel.IReply;
import net.bpelunit.model.bpel.ISequence;
import net.bpelunit.model.bpel.IVariable;
import net.bpelunit.model.bpel._2_0.Variable;
import usagetests.GatheringVisitor;

public class Main {

	static FileWriter fstream;
	static BufferedWriter out;
	static FileWriter stream;
	static FileWriter stream2;
	static FileWriter singlestream ;

	public static void main(String[] args) throws IOException {

		fstream = new FileWriter("/Users/narimanammar/Documents/workspace/bpelunit-master/net.bpelunit.model.bpel/"
				+ "resources/bpel_files.csv");
		out = new BufferedWriter(fstream);
		out.write(
				"bpel,Variables,Partner Links (services), Inhouse Invocations, Outsourced Invocations, Total Invocations, "
				+ "Sequences, Transitions/Sequence, Flows, Transitions/flow,size (KB)");
		out.write("\n");
		
		stream = new FileWriter("/Users/narimanammar/Documents/workspace/bpelunit-master/net.bpelunit.model.bpel/"
				+ "resources/transitions_bpel.csv");
		singlestream = new FileWriter("target/tests/actual/testSimpleProcessSequence.bpel");
		

		stream.write("Opi,Opj");
		stream.write("\n");
		// File bpelfile = new File("");
		// FileInputStream in;
		
		try {

			// TODO: may need to parse wsdls
			Utils.loadConfigProperties();
			
			String variableValues = Utils.configProperties.getProperty("variableValues");
			String partnerValues = Utils.configProperties.getProperty("partnerLinkValues");
			String operationValues = Utils.configProperties.getProperty("operationValues");
			String outsourcedoperationValues = Utils.configProperties.getProperty("outsourcedOperationValues");

			
			
			generateSingleProcess(stream);
			//InputStream in = new FileInputStream(bpelFile);
			//getProcessElements(in);
			
			
			
			int no = 1;
			
			while (true) {
				stream2 = new FileWriter("/Users/narimanammar/Documents/workspace/bpelunit-master/net.bpelunit.model.bpel/"
						+ "resources/transitions_bpel"
						+ no
						+ ".csv");
				
				stream2.write("Opi,Opj");
				stream2.write("\n");
				
				// generate random process objects from the set of all possible
				// values
				Set<String> valueBO = new HashSet<String>();
				String[] values = variableValues.split(",");
				Set<String> subvariables = findSubsets(values);
				valueBO.addAll(subvariables);
				Set<String> valueBO2 = new HashSet<String>();
				String[] values2 = partnerValues.split(",");
				Set<String> subpartners = findSubsets(values2);
				valueBO2.addAll(subpartners);
				Set<String> valueBO3 = new HashSet<String>();
				String[] values3 = operationValues.split(",");
				//Set<String> valueBO4 = new HashSet<String>();
				String[] values4 = outsourcedoperationValues.split(",");
				Set<String> suboperations = findSubsets(values3);
				valueBO3.addAll(suboperations);
				Set<String> suboutsourcedoperations = findSubsets(values4);
				valueBO3.addAll(suboutsourcedoperations);
				//Map<String,String> valueBO5  = findSeqSubsets((String[]) valueBO3.toArray());			
				
				IProcess p = BpelFactory.createProcess();
				p.setName("Process" + no);
				p.setTargetNamespace("ProcessNamespace");
				String wsdlNamespace = "http://www.example.org/service";

				IImport imp = p.addImport();
				imp.setImportType(IImport.IMPORTTYPE_WSDL);
				imp.setLocation("testSimpleProcessSequence.wsdl");
				imp.setNamespace(wsdlNamespace);

				// client
				IPartnerLink plc = p.addPartnerLink();
				plc.setName("client");
				plc.setPartnerLinkType(new QName(wsdlNamespace, "clientPLT"));
				plc.setPartnerRole("client");
				plc.setMyRole("compositeProvider");

				// collaborating services
				for (String v : valueBO2) {
					IPartnerLink pl = p.addPartnerLink();
					pl.setName(v);
					pl.setPartnerLinkType(new QName(wsdlNamespace, v + "PLT"));
					// TODO: how to set other services roles?
					pl.setMyRole("compositeProvider");
				}

				// variables
				IVariable request = p.addVariable();
				request.setName("request");
				request.setMessageType(new QName(wsdlNamespace, "Request"));

				IVariable response = p.addVariable();
				response.setName("response");
				response.setMessageType(new QName(wsdlNamespace, "Response"));

				// all variables exchanged between services
				for (String v : valueBO) {
					IVariable vb = p.addVariable();
					vb.setName(v);
					vb.setMessageType(new QName(wsdlNamespace, v));
					// TODO:get types from ontology/wsdl
					// TODO:associate var with wsdl in properties file

				}

				ISequence mainSequence = p.setNewSequence();
				mainSequence.setName("mainSequence");
				
				IReceive receive = mainSequence.addReceive();
				receive.setName("receive");
				receive.setCreateInstance(true);// create a process instance
				receive.setVariable(request); // e.g. gene value
				receive.setOperation("searchRepsitory");
				receive.setPartnerLink("client");
				receive.setPortType(new QName(wsdlNamespace, "lns:SearchWSPT"));

				// IAssign assign = mainSequence.addAssign();
				// assign.setName("assign");
				// ICopy copy = assign.addCopy();
				// copy.getFrom().setVariable(request);
				// copy.getFrom().setPart("parameters");
				// copy.getFrom().setNewQuery().setQueryContents("in");
				// copy.getTo().setVariable(response);
				// copy.getTo().setPart("parameters");
				// copy.getTo().setExpression("in");

				IFlow flow = mainSequence.addFlow(); //the main flow
				ISequence seq = flow.addSequence(); //first sequence in flow
				seq.setName("Sequence");
				
				//Transitions table
				stream2.write(receive.getOperation()+",");//root src operation
				stream2.write(valueBO3.iterator().next()+"\n");//first invoke in all flow subsequences form destinations of the root operation
				
				//invokes within first sequence
				for (String v : valueBO3) {
					IInvoke invoke = seq.addInvoke();
					invoke.setName("invoke");
					invoke.setOperation(v);	
					stream2.write(valueBO3.iterator().next()+",");//root src operation
					stream2.write(invoke.getOperation()+"\n");//first invoke in all flow subsequences form destinations of the root operation
					
				}
				
				ISequence seq1 = flow.addSequence(); //sec sequence in flow
				seq1.setName("sequence1");
				
				IFlow flow1 = seq1.addFlow(); //flow in sec sequence
				ISequence seq2 = flow1.addSequence();
				seq2.setName("Sequence2"); //first seq in subflow
				//another invokes set in this sequence
				suboperations = findSubsets(values3);
				valueBO3.addAll(suboperations);
			    suboutsourcedoperations = findSubsets(values4);
				valueBO3.addAll(suboutsourcedoperations);
				
				//Transitions table
				stream2.write(receive.getOperation()+",");//root src operation
				stream2.write(valueBO3.iterator().next()+"\n");//first invoke in all flow subsequences form destinations of the root operation
				
				for (String v : valueBO3) {
					IInvoke invoke = seq2.addInvoke();
					invoke.setName("invoke");
					invoke.setOperation(v);
					stream2.write(valueBO3.iterator().next()+",");//root src operation
					stream2.write(invoke.getOperation()+"\n");//first invoke in all flow subsequences form destinations of the root operation
					
				}
				
				ISequence seq3 = flow1.addSequence();//sec seq in subflow
				seq3.setName("Sequence3");
				
				
				//another invokes set in this sequence
				suboperations = findSubsets(values3);
				valueBO3.addAll(suboperations);
			    suboutsourcedoperations = findSubsets(values4);
				valueBO3.addAll(suboutsourcedoperations);
				
				//Transitions table
				stream2.write(receive.getOperation()+",");//root src operation
				stream2.write(valueBO3.iterator().next()+"\n");//first invoke in all flow subsequences form destinations of the root operation
				
				for (String v : valueBO3) {
					IInvoke invoke = seq3.addInvoke();
					invoke.setName("invoke");
					invoke.setOperation(v);
					stream2.write(valueBO3.iterator().next()+",");//root src operation
					stream2.write(invoke.getOperation()+"\n");//first invoke in all flow subsequences form destinations of the root operation
					
				}
				
				java.util.Iterator<String> itr =  valueBO3.iterator();
			    Object lastElement = itr.next();
			    while(itr.hasNext()) {
			        lastElement=itr.next();
			    }
				stream2.write(lastElement+",");//root src operation
				
				//another invokes set in seq1
				suboperations = findSubsets(values3);
				valueBO3.addAll(suboperations);
			    suboutsourcedoperations = findSubsets(values4);
				valueBO3.addAll(suboutsourcedoperations);
			
				stream2.write(valueBO3.iterator().next()+"\n");//dst operation
				
				
				for (String v : valueBO3) {
					IInvoke invoke = seq1.addInvoke();
					invoke.setName("invoke");
					invoke.setOperation(v);
					stream2.write(valueBO3.iterator().next()+",");//root src operation
					stream2.write(invoke.getOperation()+"\n");//first invoke in all flow subsequences form destinations of the root operation
					
				}

				IReply reply = mainSequence.addReply();
				reply.setName("reply");
				reply.setVariable(response);
				reply.setOperation("operation");
				reply.setPartnerLink("processPL");
				reply.setPortType(new QName(wsdlNamespace, "Porttype"));

//				FileOutputStream fo = null;
//				try {
//					File bpelFile2 = new File("src/test/resources/generatedbpels/samplebpel.bpel");
//					bpelFile2.getParentFile().mkdirs();
//					fo = new FileOutputStream(bpelFile2);
//					p.save(fo);
//
//					
//				} finally {
//					IOUtils.closeQuietly(fo);
//				}

				FileOutputStream outputStream = new FileOutputStream((new File(".")).getCanonicalPath() + File.separator
						+ "resources" + File.separator + File.separator + "bpel" + no + ".xml");

				p.save(outputStream, null);
				out.write(no + "," + subvariables.size()+2 + "," + subpartners.size()+","+ suboperations.size()  
				+ ","+suboutsourcedoperations.size()+","+(suboperations.size()+suboutsourcedoperations.size())+
				",0,0,0,0,0");
				out.write("\n");
				// BufferedWriter writer = new BufferedWriter(new
				// OutputStreamWriter(outputStream));

				// writer.write();
				// writer.close();
				outputStream.close();
				//System.out.println("INFO : BPEL is created with  Id " + no);

				stream2.close();
				//System.out.println("INFO : BPEL Transitions are created with  Id " + no);

				// System.out.println(p.toString());
				// out.write();
				// out.write("\n");
				no++;

				if (no > Integer.parseInt(Utils.configProperties.getProperty("noOfProcesses"))) {
					break;
				}
			}
			out.close();
			
		
			// OutputStream out = new OutputStream() {
			//
			// @Override
			// public void write(int b) throws IOException {
			// FileWriter fstream = new FileWriter("BPEL_50.csv");
			// BufferedWriter out = new BufferedWriter(fstream);
			// out.write(b);
			//
			// }
			// };

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private static void getProcessElements(InputStream in) throws IOException {
		IProcess p = BpelFactory.loadProcess(in);
		IActivity s = p.getMainActivity();
		
		if (s instanceof ISequence){
			
			List<IActivity> list =(List<IActivity>) ((ISequence)s).getActivities();
			
			for (IActivity a: list){
				if(a instanceof IInvoke){
					((IInvoke)a).getOperation();
				}
			}
		}
	}

	public static Set<String> findSubsets(String array[]) throws Exception {

		Set<String> set = new HashSet<String>();
		if (array.length == 1) {
			
			return set;
		}
		Random random = new Random();
		int noElement = random.nextInt(array.length);

		// to avoid empty sets
		if (noElement <5)
			noElement = 5;

		for (int i = 0; i < noElement; i++) {
			set.add(array[random.nextInt(array.length)]);
		//	System.out.println(array[random.nextInt(array.length)].toString());
		}

		return set;
	}

	public static Map<String, String> findSeqSubsets(String array[]) throws Exception {

        Map<String, String> set = new HashMap<String, String>();

        if (array.length == 1) {
            return set;
        }

        Random random = new Random();

        int noElement = random.nextInt(array.length);

        //to avoid empty sets
        if (noElement < 5) {
            noElement = 5;
        }
       // System.out.println("# transitions:" + noElement);

        for (int i = 0; i < noElement; i++) {
            int src = 0, dst = 0;
            while (src == dst) {
                src = random.nextInt(array.length);
                dst = random.nextInt(array.length);
            }
            set.put(array[src], array[dst]);
         //   System.out.println("transition" + array[src] + "," + array[dst]);

        }

        return set;
    }
	
	static void visitSingleProcess (IProcess p, Object... activities) {
			GatheringVisitor v = new GatheringVisitor(p, activities);
			p.visit(v);
			List<Object> additionalObjects = v.getAdditionalObjects();
			System.out.println("visiting  process---");
			for(Object o: additionalObjects){
				System.out.println("object:"+o.toString());
				if(o instanceof Variable) {
					List<? extends IDocumentation> documentation = ((IVariable) o).getDocumentation();
					if(documentation.size()!=0){
					List<Node> documentationElements = documentation.get(0).getDocumentationElements();
					for(Object e: documentationElements)
						System.out.println(((Element)e).getAttribute("pl").toString());
					}
				}
			}
			
			System.out.println("finished visiting  process--");
		
	}
	
	static void generateSingleProcess(FileWriter stream) throws IOException{
		IProcess p = BpelFactory.createProcess();
		p.setName("Process_single");
		p.setTargetNamespace("ProcessNamespace");
		String wsdlNamespace = "http://www.example.org/service";
		String owlNamespace ="mc";
		String privacyLevelsNamespace = "privacylevelslattic";
	

		IImport imp = p.addImport();
		imp.setImportType(IImport.IMPORTTYPE_WSDL);
		imp.setLocation("testSimpleProcessSequence.wsdl");
		imp.setNamespace(wsdlNamespace);

		// client
		IPartnerLink plc = p.addPartnerLink();
		plc.setName("client");
		plc.setPartnerLinkType(new QName(wsdlNamespace, "clientPLT"));
		plc.setPartnerRole("client");
		plc.setMyRole("compositeProvider");

		// variables
		IVariable request = p.addVariable();
		request.setName("request");
		request.setMessageType(new QName(wsdlNamespace, "Request"));

		IVariable response = p.addVariable();
		response.setName("response");
		response.setMessageType(new QName(wsdlNamespace, "Response"));
		
		IVariable genome = p.addVariable();
		genome.setName("gene");
		genome.setMessageType(new QName(owlNamespace,"Gene"));
		IDocumentation d = genome.addDocumentation();
		Element e1 = d.addDocumentationElement(new QName(privacyLevelsNamespace,"pl"));
		e1.setAttribute("pl", "H");
		Element e2 = d.addDocumentationElement(new QName(privacyLevelsNamespace,"pl"));
		e2.setAttribute("pl", "MH");
		
	
		ISequence mainSequence = p.setNewSequence();
		mainSequence.setName("mainSequence");
		
		IReceive receive = mainSequence.addReceive();
		receive.setName("receive");
		receive.setCreateInstance(true);// create a process instance
		receive.setVariable(request); // e.g. gene value
		receive.setOperation("searchRepsitory");
		receive.setPartnerLink("client");
		receive.setPortType(new QName(wsdlNamespace, "lns:SearchWSPT"));

		 IAssign assign = mainSequence.addAssign();
		 assign.setName("assign");
		 ICopy copy = assign.addCopy();
		 copy.getFrom().setVariable(request);
		 copy.getFrom().setPart("parameters");
		 copy.getFrom().setNewQuery().setQueryContents("in");
		 copy.getTo().setVariable(response);
		 copy.getTo().setPart("parameters");
		 copy.getTo().setExpression("in");
		
		IFlow flow = mainSequence.addFlow();
		ISequence seq = flow.addSequence();
		IInvoke inv = seq.addInvoke();
		inv.setName("invoke");
		inv.setOperation("OpA2");
		//Transitions table
		stream.write(receive.getOperation()+",");//root src operation
		stream.write(inv.getOperation()+"\n");//first invoke in all flow subsequences form destinations of the root operation
		
		ISequence seq1 = flow.addSequence();
		seq1.setName("sequence1");
		IFlow flow1 = seq1.addFlow();
		ISequence seq2 = flow1.addSequence();
		seq2.setName("Sequence2");
		IInvoke inv1 = seq2.addInvoke();
		inv1.setName("invoke");
		inv1.setOperation("OpB");
		//Transitions table
		stream.write(receive.getOperation()+",");//root src operation
		stream.write(inv1.getOperation()+"\n");//first invoke in all flow subsequences form destinations of the root operation
		
		ISequence seq3 = flow1.addSequence();
		seq3.setName("Sequence3");
		IInvoke inv2 = seq3.addInvoke();
		inv2.setName("invoke");
		inv2.setOperation("OpC");
		
		//Transitions table
		stream.write(receive.getOperation()+",");//root src operation
		stream.write(inv2.getOperation()+"\n");//first invoke in all flow subsequences form destinations of the root operation
		
		IInvoke inv3 = mainSequence.addInvoke();
		inv3.setName("invoke");
		inv3.setOperation("OpA1");
		//Transitions table
		stream.write(inv1.getOperation()+",");//root src operation
		stream.write(inv3.getOperation()+"\n");//first invoke in all flow subsequences form destinations of the root operation
		//Transitions table
		stream.write(inv2.getOperation()+",");//root src operation
		stream.write(inv3.getOperation()+"\n");//first invoke in all flow subsequences form destinations of the root operation
		
		IInvoke inv4 = mainSequence.addInvoke();
		inv4.setName("invoke");
		inv4.setOperation("OpA3");
		//Transitions table
		stream.write(inv3.getOperation()+",");//root src operation
		stream.write(inv4.getOperation()+"\n");//first invoke in all flow subsequences form destinations of the root operation
		
	
		IReply reply = mainSequence.addReply();
		reply.setName("reply");
		reply.setVariable(response);
		reply.setOperation("operation");
		reply.setPartnerLink("processPL");
		reply.setPortType(new QName(wsdlNamespace, "Porttype"));

		visitSingleProcess(p, mainSequence, assign, flow, inv3, inv4,
				receive, reply);
				
		FileOutputStream fo = null;
		try {
			File bpelFile = new File("resources/simpleprocess.bpel");
			bpelFile.getParentFile().mkdirs();
			fo = new FileOutputStream(bpelFile);
			p.save(fo);

		} finally {
			IOUtils.closeQuietly(fo);
		}
		stream.close();
		
		
	}

}
