/**
 * 
 */
var req;
var isIE;
var completeField;
var completeTable;
var autoRow;

function init() {
	completeField = document.getElementById("complete-field");
	completeTable = document.getElementById("complete-table");
	autoRow = document.getElementById("auto-row");
	completeTable.style.top = getElementY(autoRow) + "px";
}

function doCompletion() {
	/**
	 * escape() function encodes a string.
	 * This function makes a string portable, so it can be transmitted across any network to any computer that supports ASCII characters.
	 * This function encodes special characters, with the exception of: * @ - _ + . /
	 */
	//autocomplete is the servlet name
	//action a parameter
	//id is the value from the inputField
	var url = "exampleAjax?action=complete&id=" + escape(completeField.value);
	req = initRequest();
	//GET, the http method
	//true, signifying that the interaction is asynchronous
	req.open("GET", url, true);
	//since the function works asynchronously, a call back method is specified
	req.onreadystatechange = callback;
	//HTTP interaction begins when XMLHttpRequest.send()
	req.send(null);
}

/**
 * function that identifies the browser version, then return XMLHttpRequest
 * @returns
 */
function initRequest() {
	if (window.XMLHttpRequest) {
		if (navigator.userAgent.indexOf('MSIE') != -1) {
			isIE = true;
		}
		return new XMLHttpRequest();
	} else if (window.ActiveXObject) {//the XMLHttpRequest equivalent for Internet Explorer 6
		isIE = true;
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function callback() {
	clearTable();
	if (req.readyState == 4) {
		if (req.status == 200) {
			parseMessages(req.responseXML);
		}
	}
}

function appendComposer(firstName, lastName, composerId) {

	var row;
	var cell;
	var linkElement;

	if (isIE) {
		completeTable.style.display = 'block';
		row = completeTable.insertRow(completeTable.rows.length);
		cell = row.insertCell(0);
	} else {
		completeTable.style.display = 'table';
		row = document.createElement("tr");
		cell = document.createElement("td");
		row.appendChild(cell);
		completeTable.appendChild(row);
	}

	cell.className = "popupCell";

	linkElement = document.createElement("a");
	linkElement.className = "popupItem";
	linkElement.setAttribute("href", "autocomplete?action=lookup&id="
			+ composerId);
	linkElement
			.appendChild(document.createTextNode(firstName + " " + lastName));
	cell.appendChild(linkElement);
}

function getElementY(element) {

	var targetTop = 0;

	if (element.offsetParent) {
		while (element.offsetParent) {
			targetTop += element.offsetTop;
			element = element.offsetParent;
		}
	} else if (element.y) {
		targetTop += element.y;
	}
	return targetTop;
}

function clearTable() {
	if (completeTable.getElementsByTagName("tr").length > 0) {
		completeTable.style.display = 'none';
		for (loop = completeTable.childNodes.length - 1; loop >= 0; loop--) {
			completeTable.removeChild(completeTable.childNodes[loop]);
		}
	}
}

function parseMessages(responseXML) {

	// no matches returned
	if (responseXML == null) {
		return false;
	} else {

		var composers = responseXML.getElementsByTagName("composers")[0];

		if (composers.childNodes.length > 0) {
			completeTable.setAttribute("bordercolor", "black");
			completeTable.setAttribute("border", "1");

			for (loop = 0; loop < composers.childNodes.length; loop++) {
				var composer = composers.childNodes[loop];
				var firstName = composer.getElementsByTagName("firstName")[0];
				var lastName = composer.getElementsByTagName("lastName")[0];
				var composerId = composer.getElementsByTagName("id")[0];
				appendComposer(firstName.childNodes[0].nodeValue,
						lastName.childNodes[0].nodeValue,
						composerId.childNodes[0].nodeValue);
			}
		}
	}
}