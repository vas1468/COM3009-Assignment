package Questions;

public class Exercise3_VigenerePlus {
	/*
	 * author:vk00050
	 */
	public static void main(String[] args) {

		String key = "BALE";
		String ciphertext = "rn fpuua, eop het in dpdkaoe lb ajn cop, ohjq hca wm ipki e lkacbi fv nhpcy ayj uuhdboa " +
				"el oo al akydmo azxfilosfj rf jjmzprfn kw kfrfupnj. Gico O ieu efb dwo ky plrnua, A lrfdsug etjpfj rf " +
				"drkd kpr ohj aapdgh bcao iawzm rn rs cayjl ayj yazc; bng ppfscu sy idin ky ric larc doybzuyb ajlb tah " +
				"orbnr zu lb zq uawmo ewhpnbd, li R jlnj zfpzuafx inraofrjp.  P bcuy bdsd kyz rs lxnqaazfjm, lru glaaoyo " +
				"bffua ay hdwa, brdk bcu oauu jel a kaarkc yakfjn, P gbpnj ajakpei bcu raupkc tooh zq zewef, eop lpwoccu " +
				"mlvj ao bcu akxak ddho ky Dpfkomua.  Ric jimcwsa eop ial gikku zkpho iasfj dn ohj mikwu, nzsuzbrnb bcu " +
				"pilwn ky rial eacbr ljeuybphn.  Bcux ibg ohj miasm rkeu ukwglpu oy e kaaoj jlfy-ifko, fvb fsvfu nfb uohqjhy " +
				"yj, gik tah us rf yx facbmo ao glbjh. Jhjn P eublrfug bd ric zogjku fv ajn qcayrjd, ohjq jcwu xca ifpj oy dboy, " +
				"xjklulu P gbm vruua gbrnp as rs ouzm.  Ajn czxnpdh zkokkuuug yj rf xj paktnnj, bng bcao bcu nrjix'i ydnca gli " +
				"bxmpdazhrnb ao a iklrpfj ilroua: xpr ic tah idsy cbmnj d"
 +"z ial znewm; ukw ric fjlrouk owsjaoe hhbdkktua ceuas lrnd R iljj, O zezu pr b mikwr oazu jaajpr culprnb, " +
 "eop ikkpprn wm bcu nru su bcu zeqdn, xx giafj ohj zkcjr jel zliauycu, O zprug ao a kkpp ckrkn, 'Fdnb frbn " +
 "bcu rklr murmheor daoe fv Kokfrxvb!'  Ajpi nhnea xaaokn pjknaeug yj ao yx dlruoyo tooh bdk ddmhaqdn cokfyrwri, " +
 "bng qwulbjp rc b _nlpuaz_ ussy bcu hddr, jhrki al ric cobhjmo brrkc dz ikosvp bifrn ricz.  Jpi zawclrx jjmppjp " +
 "P gdwkj aadc lsrc dricw smddhouooos dz sprnbaoe lfk ric wuhb dz ial uyczq'h icomi rnok coh ddhoi.  Bng id wyyjahuwulxku" +
 " pi ajn ezfpbrsy ky xaaokni, ajlb cu hcjinj as ohrnd ky nfbcoyo kuhi ajlr wugufoyo ajn gcskc jimawu fv Qdnvpmzu " +
 "rnok b xakeoyqj, ayj nsccwnprn oo, xx a cafuakx; su jjmopdqprn ric Qob-coppeo uqakuh, eop zkzxnfkoyo ajlb sufdku " +
 "ok qhnev ric lilfkua cop fv ajnaw ubol, fx gcozh cu jkpdg pjilao ric lskc zsyewki ky ric tjffj gfpkp.  Sua O nruulndwacu" +
 " rf jrbnpa jpy yhfy ajpi uuhann, ss zays bhbuzuybl paetn updi ohj rfdrkh ky xffrkx el gnfk a"
 +"h tpmoafu; lru O mfboyfx xakauhbjp, 'ohbr P gdwkj ouccw fn eo' ";

		int[] mult = new int[key.length()];
		
		mult[0] = 15;
		mult[1] = 5;
		mult[2] = 7;
		mult[3] = 19;
		
		String plaintext = "";

		/* all my ciphertext to Uppar case to be able to manipulate all the characters the same way
		 in a more simple way */
		ciphertext = ciphertext.toUpperCase();
		
		int thesis = 0;
		// A for loop through my ciphertext
		for (int i = 0; i < ciphertext.length(); i++) {
			int ckey = (key.charAt(thesis) - 65);
			int ciph = (ciphertext.charAt(i) - 65);

			//checking for specific characters in the ciphertext to ignore them.
			if ((ciph + 65) == 32) {
				plaintext += " ";
			} else if ((ciph + 65) == 46) {
				plaintext += ".";
			} else if ((ciph + 65) == 59) {
				plaintext += ";";
			} else if ((ciph + 65) == 44) {
				plaintext += ",";
			} else if ((ciph + 65) == 45) {
				plaintext += "-";
			} else if ((ciph + 65) == 95) {
				plaintext += "_";
			} else if ((ciph + 65) == 58) {
				plaintext += ":";
			} else if ((ciph + 65) == 39) {
				plaintext += "'";
			} else if ((ciph + 65) == 33) {
				plaintext += "!";
			
			} else {
				for (int plain = 0; plain <= 26; plain++) {
					if (((plain * mult[thesis]) + ckey) % 26 == ciph) {
						plaintext += ((char) (plain + 65));
						break;
					}
				}
				thesis = ++thesis % key.length();
			}
		}	
		plaintext = plaintext.toLowerCase();
		System.out.println(plaintext);

	}
}