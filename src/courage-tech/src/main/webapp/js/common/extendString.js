String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)|($\s*)/g, "");
};

String.prototype.escapeHtml = function() {
	return this.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/\"/g, "&quot;");
};

String.prototype.replaceAll = function(oldStr, newStr) {
	return this.split(oldStr).join(newStr);
};

if (!String.prototype.startsWith) {
	String.prototype.startsWith = function(str) {
		return this.lastIndexOf(str, 0) === 0;
	};
}