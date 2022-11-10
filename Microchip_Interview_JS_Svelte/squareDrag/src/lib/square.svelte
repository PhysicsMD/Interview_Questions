<script>
  import { trusted } from "svelte/internal";
  import OptionsTab from "./optionsTab.svelte";
  import CoordTab from "./coordinatesTab.svelte";

  // Decided to make the box coordinates and offset
  // integers instead of an array for readability
  var boxX, boxY, boxXOffset, boxYOffset
	boxX = boxY = boxXOffset = boxYOffset = 0
  let boxWidth = 50
  var direction = [0,0]
  let timerID = null
  var isMouseFollowed , isbound, isDVDbounce, isThrow 
  isMouseFollowed = isbound = isDVDbounce = isThrow= false
  let oldPos = [0,0]
  let newPos = [0,0]
  const mouseIsMoving = (e) => {
    if(isMouseFollowed){
      if(isbound || isThrow){
        if(e.clientX+boxXOffset < 0){
          boxX = 0
        }
        else if(e.clientX + boxXOffset+boxWidth > window.innerWidth){
          boxX = window.innerWidth - boxWidth
        }
        else{
          boxX = e.clientX + boxXOffset
        }
        if(e.clientY+boxYOffset < 0){
          boxY = 0
        }
        else if(e.clientY+boxWidth+boxYOffset > window.innerHeight){
          boxY = window.innerHeight - boxWidth
        }
        else{
          boxY = e.clientY + boxYOffset
        }
        // for direction
        oldPos = newPos
        newPos = [boxX, boxY]
      }
      else {
        boxX = e.clientX + boxXOffset
        boxY = e.clientY + boxYOffset
      }
    }
  }
  const followmouse = (e) => {
    if(!isDVDbounce){
      isMouseFollowed = true
		  // This adds an offset for where the mouse clicked on the box
		  boxXOffset = boxX - e.clientX
		  boxYOffset = boxY - e.clientY
      if(isThrow){
        clearInterval(timerID)
        oldPos = newPos = [boxX, boxY]
      }
    }
  }
  const thrown = () => {
    boxX += direction[0]
    boxY += direction[1]
    let friction = .95
    let gravity = .75
    if(boxX+boxWidth >= window.innerWidth || boxX <= 0){
      direction[0] *= -1
    }
    if(Math.abs(direction[0]) < .001){
      // prevent over calculation
      direction[0] = 0
    }
    direction[0] *= friction

    if(boxY+boxWidth >= window.innerHeight){
      direction[1] = 0
      boxY = window.innerHeight-boxWidth
    }
    direction[1] += gravity
  }
  const unfollowmouse = (e) => {
    isMouseFollowed = false
    if(isThrow){
      direction = [newPos[0] - oldPos[0], newPos[1] - oldPos[1]]
      timerID = setInterval(thrown, 15)
    }
  }
  const bounce = () => {
    // Picks a random direction, makes sure the direction 
    // is not horizontal or verticle
    if(direction[0] == 0 && direction[1] == 0){
      direction[0] = (Math.floor(Math.random()*5)+1)
      direction[1] = (Math.floor(Math.random()*5)+1)
    }
    // When square hits boundary, go other way
    if(boxX+boxWidth > window.innerWidth || boxX < 0){
      direction[0] *= -1
    }
    if(boxY+boxWidth > window.innerHeight || boxY < 0){
      direction[1] *= -1
    }
    boxX += direction[0]
    boxY += direction[1]
  }
  const selector = (e) => {
    if(e.target.value == "reset"){
      boxX = 0
      boxY = 0
    }
    isbound = "Bind" == e.target.value
    isDVDbounce = "dvdb" == e.target.value
    if(isDVDbounce){
      timerID = setInterval(bounce, 15)
    }
    else {
      // This clears any timer to make space for a new one
      clearInterval(timerID)
      // Direction reset prevents the same bounce direction
      direction = [0,0]
    }
    isThrow = "throw" == e.target.value
  }
  document.addEventListener("mousemove", mouseIsMoving)
</script>

<div class="square" on:mousedown={followmouse} on:mouseup={unfollowmouse} style="width: {boxWidth}px; top: {boxY}px; left: {boxX}px"></div>

<CoordTab {boxX} {boxY}/>

<OptionsTab {selector}/>

<style>
  .square {
    position: absolute;
    background-color: black;
    aspect-ratio: 1;
  }
</style>
