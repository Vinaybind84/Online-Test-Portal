var jsonData; // Variable to store JSON data
var currentIndex = 0; // Index to keep track of current record
var qno = 1;
var seconds = 30;
var a;
var b;
var c;
var d;
var item;
var score = 0;
var total = 0;
// Function to display current record
function checkAnswer() {
	if (a.checked == true && item.correct == "A") {
		score++;
	}
	else if (b.checked == true && item.correct == "B") {
		score++;
	}
	else if (c.checked == true && item.correct == "C") {
		score++;
	}
	else if (d.checked == true && item.correct == "D") {
		score++;
	}
}

function displayCurrentRecord() {
	jsonDataDisplay = document.getElementById('jsonDataDisplay');
	var lblqno = document.getElementById("lblqno");
	var lblquestion = document.getElementById("lblquestion");
	a = document.getElementById("a");
	b = document.getElementById("b");
	c = document.getElementById("c");
	d = document.getElementById("d");
	var lblA = document.getElementById("lblA");
	var lblB = document.getElementById("lblB");
	var lblC = document.getElementById("lblC");
	var lblD = document.getElementById("lblD");
	a.checked = false; b.checked = false; c.checked = false; d.checked = false;
	if (jsonData && jsonData.length > 0) {
		item = jsonData[currentIndex];
		lblqno.innerHTML = qno;
		lblquestion.innerHTML = item.question;
		lblA.innerHTML = item.a;
		lblB.innerHTML = item.b;
		lblC.innerHTML = item.c;
		lblD.innerHTML = item.d;
	}

}

// Event listener for button click
$(document).ready(function() {
	jsonData = $("#jsonData").val();
	jsonData = JSON.parse(jsonData);
	// Display initial record					
	displayCurrentRecord();
	var timerDisplay = document.getElementById('timer');
	function startTimer() {
		timer = setInterval(countdown, 1000); // Update every second (1000 milliseconds)
	}
	function countdown() {
		seconds--;
		timerDisplay.textContent = seconds;

		if (seconds <= 0) {
			clearInterval(timer);
			// Add any logic you want to execute after the timer ends

			checkAnswer();
			currentIndex++;
			qno++;
			if (currentIndex == jsonData.length - 1) {
				clearInterval(timer);
				total = jsonData.length;
				window.location.href = 'testover?t=' + total + '&s=' + score;
			}
			displayCurrentRecord();
			clearInterval(timer); // Clear existing timer
			seconds = 30; // Reset seconds
			timerDisplay.textContent = seconds; // Update timer display
			startTimer(); // Start timer again
		}
	}



	$('#ButtonNext').on('click', function() {
		// Move to next record

		checkAnswer();
		currentIndex++;
		qno++;
		// Check bounds

		if (currentIndex == jsonData.length - 1) {
			clearInterval(timer);
			total = jsonData.length;
			window.location.href = 'testover=' + total + '&s=' + score;
		}
		// Display current record
		displayCurrentRecord();
		clearInterval(timer); // Clear existing timer
		seconds = 30; // Reset seconds
		timerDisplay.textContent = seconds; // Update timer display
		startTimer(); // Start timer again
	});
	startTimer();
});


