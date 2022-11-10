<script>
    export let selector
</script>

<div class="optionsTab">
    <div>></div>
    <div>
        <form>
            <fieldset id="options" style="border: none;" on:input={selector}>
                <input type="radio" id="reset" name="options" value="reset">
                <label for="reset"> Reset</label><br>
      
                <input type="radio" id="unBind" name="options" value="unBind" checked>
                <label for="unBind"> Unbound?</label><br>
  
                <input type="radio" id="Bind" name="options" value="Bind">
                <label for="Bind"> Bound?</label><br>
  
                <input type="radio" id="dvdb" name="options" value="dvdb">
                <label for="dvdb"> DVD Bounce</label><br>
  
                <input type="radio" id="throw" name="options" value="throw">
                <label for="throw"> Throw</label><br>
            </fieldset>
        </form>
    </div>
</div>

<style>
    label {
		display: inline-block;
	}

    .optionsTab {
        z-index: 2;
        position: absolute;
	    color: white;
        background-color: #999;
        border-radius: 15px 0px 0px 15px;
        width: 50px;
        height: 50px;
        top: 10px;
        right: 5px;
        box-shadow: 5px 0px 0px 0px #999;
        transition: all 1s ease-in-out;
        overflow: hidden;
    }

    .optionsTab:hover{
        transition: all 1s ease-in-out;
        width: 150px;
        height: auto;
        padding-top: 5px;
        text-align: left;
    }

    .optionsTab div:first-child {
        visibility: visible;
        font-size: 50px;
	    line-height:15px;
        /* padding top is 17px local and 12px in repl*/
        padding: 12px 0px 15px 10px;
    }

    .optionsTab:hover div:first-child {
        display:none;
        transition-duration: 1s;
    }

    .optionsTab div:not(:first-child) {
        width: 400px;
        padding-left:200px;
        transition-delay: 1s;
    }

    .optionsTab:hover div:not(:first-child) {
        visibility: visible;
        transition-delay: 1s;
        padding-left:0px;
    }
</style>
