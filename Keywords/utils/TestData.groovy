package utils

class TestDataUtil {

	static boolean isPositive(String expectSuccess) {
		return expectSuccess.toBoolean()
	}
	
	static boolean isValidEmailFormat(String email) {
		if (email == null) return false
		def e = email.trim()
		// simple enough for test purpose
		return (e ==~ /^[^\s@]+@[^\s@]+\.[^\s@]+$/)
	}
	
}
